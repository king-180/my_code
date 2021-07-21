package com.demo.order.feign;

import com.demo.order.vo.FreightFareVo;
import com.demo.order.vo.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/21 16:55
 */
@FeignClient("gulimall-ware")
public interface WareFeignService {

    @RequestMapping("/ware/hasStock")
    List<SkuHasStockVo> getSkuStock(@RequestParam("skuIds") List<Long> skuIds);

    @RequestMapping("/ware/getFare")
    FreightFareVo getFare(@RequestParam("addrId") Long addrId);

}
