package com.demo.cart.feign;

import com.demo.cart.vo.SkuInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/21 11:02
 */
@Service
public interface ProductFeignService {

    @GetMapping("/product/skuInfo/{skuId}")
    SkuInfoVo getSkuInfo(@PathVariable("skuId") Long skuId);

    @GetMapping("/product/skuAttr/{skuId}")
    List<String> getSkuSaleAttr(@PathVariable("skuId") Long skuId);
}
