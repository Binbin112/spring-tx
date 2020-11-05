package com.smart.tx.mapper;

import com.smart.tx.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    /**
     * 通过id查询产品信息
     *
     * @param ids
     * @return
     */
    List<Product> selectListByIds(@Param("ids") List<Long> ids);

}
