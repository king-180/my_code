package com.demo.order.vo;

import com.demo.order.entity.OrderEntity;
import lombok.Data;

/**
 * @author wangxing
 * @date 2021/7/21 18:30
 */
@Data
public class OrderSubmitResponse {

    private Integer code;

    private OrderEntity order;

}
