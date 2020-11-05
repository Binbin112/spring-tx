package com.smart.tx.common.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestParams {

    private Integer userId;
    // 收货人的姓名
    private String username;
    private String phone;
    private String address;
    /**
     * 购买的商品信息
     */
    private List<OrderItemRequestParam> productList;
    // 优惠券
    private DiscountReqeustParam discount;
}
