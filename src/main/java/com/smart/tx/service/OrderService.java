package com.smart.tx.service;

import com.smart.tx.common.ResponseEntity;
import com.smart.tx.common.request.OrderRequestParams;
import com.smart.tx.common.vo.OrderVo;

/**
 *
 */
public interface OrderService {
    /**
     * 创建订单功能
     *
     * @return
     */
    ResponseEntity<OrderVo> createOrder(OrderRequestParams orderRequest) throws Exception;

}
