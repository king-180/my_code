package com.wx.demo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangxing
 * @date 2021/4/25 23:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractPageLimiter {

    private Integer pageNo;
    private Integer pageSize;

}
