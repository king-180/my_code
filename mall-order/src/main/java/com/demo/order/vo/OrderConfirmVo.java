package com.demo.order.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wangxing
 * @date 2021/7/21 14:38
 */
public class OrderConfirmVo {

    @Getter
    @Setter
    private List<MemberReceiveAddressVo> address;

    @Getter
    @Setter
    private List<OrderItemVo> orderItem;

    @Getter
    @Setter
    private Map<Long, Boolean> stocks;

    /**
     * 防重令牌
     */
    @Getter
    @Setter
    private String orderToken;

    /**
     * 优惠券信息
     */
    @Getter
    @Setter
    private Integer integration;

/*    @Getter
    @Setter
    private BigDecimal totalAmount;

    @Getter
    @Setter
    private BigDecimal payAmount;*/

    public Integer getCount() {
        Integer cnt = 0;
        if (CollectionUtils.isNotEmpty(orderItem)) {
            for (OrderItemVo item : orderItem) {
                cnt += item.getCount();
            }
        }
        return cnt;
    }

}
