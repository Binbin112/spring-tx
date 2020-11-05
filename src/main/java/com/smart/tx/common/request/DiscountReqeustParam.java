package com.smart.tx.common.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 优惠券请求参数封装类
 * 优惠券ID
 * 金额
 */
@Data
public class DiscountReqeustParam {
    private Integer discountId;
    // 优惠金额
    private String money;
}
