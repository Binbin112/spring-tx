package com.smart.tx.mapper;

import com.smart.tx.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    int insert(@Param("orderItem") OrderItem orderItem);
}