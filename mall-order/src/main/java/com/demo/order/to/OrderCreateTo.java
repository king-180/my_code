package com.demo.order.to;

import com.demo.order.entity.OrderEntity;
import com.demo.order.vo.OrderItemVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/21 19:16
 */
@Data
public class OrderCreateTo {

    private OrderEntity order;

    private List<OrderItemVo> orderItems;

    private BigDecimal payPrice;

    private BigDecimal fare;

}
