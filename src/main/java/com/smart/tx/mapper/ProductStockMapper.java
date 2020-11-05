package com.smart.tx.mapper;

import com.smart.tx.entity.ProductStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    /**
     * 经验
     *
     * @return
     */
    List<ProductStock> selectByProductIds(@Param("ids") List<Long> ids);
}
