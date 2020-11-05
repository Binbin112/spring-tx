package com.smart.tx.common.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单号
 * 订单的标题
 * 订单信息
 */
@Data
public class OrderVo {
    private String orderSn;
    private String subject;
    private BigDecimal total;
}
