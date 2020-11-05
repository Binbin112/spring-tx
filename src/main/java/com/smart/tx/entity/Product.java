package com.smart.tx.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Integer productId;
    private String name;
    private BigDecimal price;
    private String img;
}
