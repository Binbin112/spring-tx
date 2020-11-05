package com.smart.tx.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
    * 订单详情表
    */
@Data
public class OrderItem {
    private Long id;

    /**
    * 订单id
    */
    private Long orderId;

    /**
    * 订单编号
    */
    private String orderNo;

    /**
    * 商品id
    */
    private Long productId;

    /**
    * 商品图片
    */
    private String productPic;

    /**
    * 商品名称
    */
    private String productName;

    /**
    * 销售价格
    */
    private BigDecimal productPrice;

    /**
    * 购买数量
    */
    private Integer productQuantity;
}