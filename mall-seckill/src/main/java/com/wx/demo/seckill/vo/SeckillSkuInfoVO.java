package com.wx.demo.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangxing
 * @date 2021/7/19 10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillSkuInfoVO {

    private Long id;

    private Long skuId;

    private BigDecimal secskillPrice;

    private Date startTime;

    private Date endTime;

    private int seckillCount;

}
