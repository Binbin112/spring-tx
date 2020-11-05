package com.smart.tx.mapper;

import com.smart.tx.entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int insert(@Param("order") Order order);

}