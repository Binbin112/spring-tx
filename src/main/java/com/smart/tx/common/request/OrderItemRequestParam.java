package com.smart.tx.common.request;

import lombok.Data;

@Data
public class OrderItemRequestParam {
    private Long productId;
    private String name;
    private String price;
    private String img;
    //  购买数量
    private Integer quantity;
}
