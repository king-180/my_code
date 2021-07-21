package com.wx.demo.seckill.feign;

import com.wx.demo.seckill.vo.SeckillSkuInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/19 0:39
 */
@Component
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @RequestMapping("/seckill")
    List<SeckillSkuInfoVO> getSeckillSkuInfo();

}
