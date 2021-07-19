package com.wx.demo.seckill.feign.impl;

import com.wx.demo.seckill.feign.CouponFeignService;
import com.wx.demo.seckill.vo.SeckillSkuInfoVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/19 11:30
 */
@Service
public class CouponFeignServiceImpl implements CouponFeignService {

    static List<SeckillSkuInfoVO> skuInfoList;

    static {
        skuInfoList = Arrays.asList(new SeckillSkuInfoVO(1L,1001L, new BigDecimal("99.00"), new Date(), new Date(), 10),
                new SeckillSkuInfoVO(2L,1002L, new BigDecimal("99.00"), new Date(), new Date(), 20),
                new SeckillSkuInfoVO(1L,1003L, new BigDecimal("99.00"), new Date(), new Date(), 100),
                new SeckillSkuInfoVO(3L,1004L, new BigDecimal("99.00"), new Date(), new Date(), 50),
                new SeckillSkuInfoVO(1L,1005L, new BigDecimal("99.00"), new Date(), new Date(), 200));
    }


    @Override
    public List<SeckillSkuInfoVO> getSeckillSkuInfo() {
        return skuInfoList;
    }
}
