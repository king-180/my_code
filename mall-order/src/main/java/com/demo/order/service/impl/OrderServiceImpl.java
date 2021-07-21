package com.demo.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.demo.order.entity.OrderEntity;
import com.demo.order.entity.OrderItemEntity;
import com.demo.order.feign.CartFeignService;
import com.demo.order.feign.MemberFeignService;
import com.demo.order.feign.WareFeignService;
import com.demo.order.interceptor.UserLoginInterceptor;
import com.demo.order.service.OrderService;
import com.demo.order.to.OrderCreateTo;
import com.demo.order.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/7/21 14:51
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private CartFeignService cartFeignService;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ThreadPoolExecutor executor;

    private static ThreadLocal<OrderSubmitVo> orderSubmitVoThreadLocal = new ThreadLocal<>();

    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        MemberRespVO memberRespVO = UserLoginInterceptor.loginUserThreadLocal.get();
        // 取出feign拦截器的共享数据
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        CompletableFuture<Void> addressTask = CompletableFuture.runAsync(() -> {
            // 共享feign拦截器的数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            // 1.调用远程会员服务查询收货地址
            List<MemberReceiveAddressVo> address = memberFeignService.getAddress(memberRespVO.getId());
            orderConfirmVo.setAddress(address);
        }, executor);

        // TODO
        //  1.feign在远程调用之前要构造新的请求模板，丢失了请求头（没有携带cookie，没有登陆信息），调用很多拦截器
        //      RequestInterceptor interceptor ： requestInterceptors 必须配置feign的远程调用拦截器
        //  2.feign在异步调用丢失上下文问题。 feign的拦截器里的 ThreadLocal<RequestAttributes> 共享数据是在同一个线程里生效

        CompletableFuture<Void> cartItemTask = CompletableFuture.runAsync(() -> {
            // 共享feign拦截器的数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            // 2.调用远程购物车服务查询购物项
            List<OrderItemVo> items = cartFeignService.getCurrentUserCartItem();
            orderConfirmVo.setOrderItem(items);
        }, executor).thenRunAsync(() -> {
            // 3.调用远程库存服务批量查询商品是否又库存
            List<OrderItemVo> orderItem = orderConfirmVo.getOrderItem();
            List<Long> skuIds = orderItem.stream().map(OrderItemVo::getSkuId).collect(Collectors.toList());
            List<SkuHasStockVo> skuStock = wareFeignService.getSkuStock(skuIds);
            if (CollectionUtils.isNotEmpty(skuStock)) {
                Map<Long, Boolean> stocksMap = skuStock.stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::isHasStock));
                orderConfirmVo.setStocks(stocksMap);
            }
        }, executor);

        orderConfirmVo.setIntegration(memberRespVO.getIntegration());
        // 4.防重令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("order:token:" + memberRespVO.getId(), token, 30, TimeUnit.MINUTES);
        orderConfirmVo.setOrderToken(token);

        CompletableFuture.allOf(addressTask, cartItemTask).get();
        return orderConfirmVo;
    }

    @Override
    public OrderSubmitResponse submitOrder(OrderSubmitVo orderSubmitVo) {
        // 将页面提交的订单信息保存到threadLocal 同一线程内部共享数据
        orderSubmitVoThreadLocal.set(orderSubmitVo);

        MemberRespVO memberRespVO = UserLoginInterceptor.loginUserThreadLocal.get();
        OrderSubmitResponse response = new OrderSubmitResponse();
        String orderToken = orderSubmitVo.getOrderToken();
        String redisKey = "order:token:" + memberRespVO.getId();
        // lua脚本 原子校验删除。返回 0 校验失败，返回 1：令牌存在且相等并且删除成功 --> 校验成功
        String script = "if redis.call('get', KEYS[1] == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(redisKey, orderToken));

        if (result == 1L) {
            // 令牌验证通过
            response.setCode(200);
            // 下单
            OrderCreateTo order = createOrder();
        } else {
            // 令牌验证不通过
            response.setCode(400);
        }

        /*String tokenRedis = redisTemplate.opsForValue().get(redisKey);
        if (Objects.equals(orderToken,tokenRedis)) {
            // 令牌验证通过

        } else {

        }*/
        return response;
    }

    private OrderCreateTo createOrder() {

        OrderCreateTo orderCreate = new OrderCreateTo();

        String orderSn = IdWorker.getTimeId();
        OrderEntity entity = new OrderEntity();
        entity.setOrderSn(orderSn);

        OrderSubmitVo orderSubmitVo = orderSubmitVoThreadLocal.get();
        FreightFareVo fare = wareFeignService.getFare(orderSubmitVo.getAddrId());
        entity.setFreightAmount(fare.getFare());
        entity.setReceiverCity(fare.getAddress().getCity());
        entity.setReceiverDetailAddress(fare.getAddress().getDetailAddress());
        entity.setReceiverName(fare.getAddress().getName());
        entity.setReceiverPhone(fare.getAddress().getPhone());
        entity.setReceiverPostCode(fare.getAddress().getPostCode());
        entity.setReceiverProvince(fare.getAddress().getProvince());
        entity.setReceiverRegion(fare.getAddress().getRegion());

        List<OrderItemVo> cartItems = cartFeignService.getCurrentUserCartItem();

        if (CollectionUtils.isNotEmpty(cartItems)) {
            List<OrderItemEntity> orderEntities = cartItems.stream().map(cartItem -> {
                OrderItemEntity orderItemEntity = new OrderItemEntity();
                orderItemEntity.setOrderSn(orderSn);
                orderItemEntity.setSkuId(cartItem.getSkuId());
                orderItemEntity.setSkuName(cartItem.getTitle());
                orderItemEntity.setSpuPic(cartItem.getImage());
                orderItemEntity.setSkuPrice(cartItem.getPrice());
                orderItemEntity.setSkuAttrVals(StringUtils.join(cartItem.getSkuAttr(), ","));
                orderItemEntity.setSkuQuantity(cartItem.getCount());
                orderItemEntity.setGiftGrowth(cartItem.getPrice().intValue());
                orderItemEntity.setGiftIntegration(cartItem.getPrice().intValue());


                return orderItemEntity;
            }).collect(Collectors.toList());
        }

        return orderCreate;
    }

}
