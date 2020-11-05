package com.smart.tx.controller;

import com.smart.tx.common.ResponseEntity;
import com.smart.tx.common.request.OrderRequestParams;
import com.smart.tx.common.vo.OrderVo;
import com.smart.tx.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 事务失效
 * 1> 必须是 public 声明方法
 * 2> service (不要把异常吃了)不要去处理异常
 * 3> 数据库引擎  InnoDb支持事务  mysiam不支持事务
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderService orderService;

    /**
     * DO entity 对象
     * 1.作为数据层的参数或者返回值
     * VO 给前端展示用  作为Service的返回对象
     * DTO  微服务 spring cloud  分布式 Dubbo
     * 1. 可以作为控制层的入口参数
     * 2. 作为Service参数
     * 3. 如果没有vo对象 可以作为service的返回对象
     * 用户必须登录
     * 参数的校验
     * 接受参数 响应
     *
     * @param orderRequest
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<OrderVo> create(@RequestBody OrderRequestParams orderRequest) throws Exception {
//       AOP 对异常信息统一处理
        return orderService.createOrder(orderRequest);
    }


    
}
