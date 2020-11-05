package com.smart.tx.service.impl;

import com.smart.tx.common.ResponseEntity;
import com.smart.tx.common.StatusCode;
import com.smart.tx.common.request.OrderItemRequestParam;
import com.smart.tx.common.utils.SnowflakeIdUtils;
import com.smart.tx.common.vo.OrderVo;
import com.smart.tx.entity.Order;
import com.smart.tx.entity.OrderItem;
import com.smart.tx.entity.Product;
import com.smart.tx.entity.ProductStock;
import com.smart.tx.exception.ServiceException;
import com.smart.tx.mapper.OrderItemMapper;
import com.smart.tx.mapper.OrderMapper;
import com.smart.tx.mapper.ProductMapper;
import com.smart.tx.mapper.ProductStockMapper;
import com.smart.tx.service.OrderService;
import com.smart.tx.common.request.OrderRequestParams;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.创建订单
 * <p>
 * 2.取消订单
 * 1.自动取消(超过了时间没付款)   24    秒杀订单  10
 * 2.用户取消
 * 3.商家取消
 * 3.修改订单
 * 4.查看订单
 * 5.查看订单详情
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;

    /**
     * 优惠券系统
     * <p>
     * <p>
     * <p>
     * <p>
     * 1000行
     * 订单     团购订单   秒杀订单 售货
     * 1. 订单表
     * 2. 订单详情表
     * 3. 库存表
     * 4. 清理购车
     * 2. 商品价格
     * 2.1 查询商品的价格
     * 2.2 对比商品的价格有没有发送改动 ----  发生改动了  直接提示前段 让用户重新下单
     * 3> 库存
     * 判断库存是否足够, 直接取消订单
     * 4>总金额
     * 4.1> 是否参加活动,活动商品 检查用户是否有权利购买 ,购买的商品超过限制
     * 4.2> 优惠券  通用优惠券   满减优惠券  店铺优惠券  单件商品的优惠券
     * 1>拆单  平台的优惠券  105  5
     * 自营    店铺 子订单   A店铺 100   B店铺 197
     * 2>红包
     * 3> 快递  模板   包邮  商品包邮 5块
     * 订单表
     * 订单详情表
     * 1. 防止重复提交  如何实现
     * 2. 判断库存跟价格是否发生改变  库存不够
     * 3. 商品是否参加平台活动
     * <p>
     * 确认订单
     * 订单号显示
     * 总金额
     * 商品的信息展示
     * 选择支付方式  支付宝 微信 银联
     * 支付--->
     * <p>
     * 支付系统
     * <p>
     * 配置信息
     * 1> (订单号 支付方式1.支付宝 ,2 微信  3.银联)
     * 订单号  订单的标签  总金额
     * <p>
     * <p>
     * 根据条件生成支付链接  (pc h5端 移动端)
     * 支付宝支付
     * 微信支付
     * 银联支付
     * 前端点击支付
     * <p>
     * <p>
     * 支付回调接口
     *
     * @param orderRequest
     * @return
     */


    /**
     * 第一步 设置订单的基本信息
     * 收货地址
     * 生成订单号
     * 判断库存是否足够
     * 判断价格是否发生改变
     * 总金额的计算
     * 保存订单信息
     * 第二步 设置订单详情的信息
     * 订单详细信息 基本信息 + 需要订单的Id和订单编号
     * 批量保存订单详情信息
     * 修改购物车
     * 第三步  修改购车状态
     * 通过商品的Ids跟用户Id修改购物车的信息
     * 第四步 修改库存
     * 第五步 增加商品的销量
     *
     * @param orderRequest
     * @return
     * @throws Exception
     */


    @Transactional
    @Override
    public ResponseEntity<OrderVo> createOrder(OrderRequestParams orderRequest) throws Exception {
        ResponseEntity<OrderVo> responseEntity = new ResponseEntity<>();
        OrderVo orderVo = null;
        String orderSn = new SnowflakeIdUtils(3, 1).nextId() + "";
        //1. 订单表
        //2  订单详情表
        try {
            Order order = new Order();
            // 前缀+ 时间搓 + 自增 (根据公司的业务规则来定)
            //订单ID 订单号 雪花算法  美团 leaf  必须唯一  分库分表

            order.setOrderSn(orderSn);
            order.setReceiverName(orderRequest.getUsername());
            order.setReceiverPhone(orderRequest.getPhone());
            order.setReceiverDetailAddress(orderRequest.getAddress());
            //订单主表的数据
            // 计算总金额
//        (数量*当前价格) + 邮费 - 优惠券 - 红包
            BigDecimal total = new BigDecimal(0);
            List<OrderItemRequestParam> productList = orderRequest.getProductList();
            // 商品的总金额
            // 库存  商品的价格
            List<Long> ids = new ArrayList<>();
            orderRequest.getProductList().forEach(req -> {
                ids.add(req.getProductId());
            });
            //是否下架
            List<Product> products = productMapper.selectListByIds(ids);
            if (products == null || products.size() != orderRequest.getProductList().size()) {
                throw new ServiceException(StatusCode.ORDER_PRODUCT_OUT);
            }
            //如果价格有变动 直接通知重新下单
            if (isPriceChange(products, orderRequest.getProductList())) {
                throw new ServiceException(StatusCode.ORDER_PRODUCT_PRICE_CHANGE);
            }
            //判断库存是否足够
            if (!isStock(orderRequest.getProductList())) {
                throw new ServiceException(StatusCode.ORDER_PRODUCT_OUT);
            }
            //计算总金额
            for (OrderItemRequestParam productRequestParam : orderRequest.getProductList()) {
                //            通过商品的Id 查询相关的库存信息
                String price = productRequestParam.getPrice();
                int quantity = productRequestParam.getQuantity();
                BigDecimal priceDecimal = new BigDecimal(price);
                BigDecimal multiply = priceDecimal.multiply(new BigDecimal(quantity));
                total = total.add(multiply);
            }

            // 减去优惠券 业务判断
//        BigDecimal subtract = total.subtract(new BigDecimal(orderRequest.getDiscount().getMoney()));
//        // 改变优惠的状态  已使用
//        // 判断红包是否可用
//        order.setTotalAmount(subtract);
            order.setUserId(orderRequest.getUserId());
            order.setTotalAmount(total);

            orderMapper.insert(order);
            // 保存订单详情
            productList.forEach(orderItemRequestParam -> {
                OrderItem orderItem = new OrderItem();
                BeanUtils.copyProperties(orderItemRequestParam, orderItem);
                orderItem.setOrderNo(orderSn);
                orderItem.setOrderId(order.getOrderId());
                orderItemMapper.insert(orderItem);
            });
//            清除购物车
            // 确认订单
            // 订单号  商品信息
            orderVo = new OrderVo();
            orderVo.setOrderSn(orderSn);
            // xx商城 + 订单号 + 订单信息
            orderVo.setSubject("xx商城-123-订单信息");
            orderVo.setTotal(total);
            responseEntity = new ResponseEntity<>();
            responseEntity.setMsg("success");
            responseEntity.setStatus(200);
            responseEntity.setData(orderVo);
        } catch (Exception e) {
            throw new ServiceException(StatusCode.ERROR);
        }
        return responseEntity;
    }

    @Resource
    ProductMapper productMapper;

    /**
     * 判断价格是否有变动
     * true  表示 价格发生改变
     * false 表示 正常
     * 默认的排序规则是主键
     *
     * @return
     */
    private boolean isPriceChange(List<Product> products, List<OrderItemRequestParam> list) {
        for (int i = 0; i < products.size(); i++) {
            // products.get(i).getPrice()  从数据库获取的对象
            //list.get(i).getPrice()  String类型
            if (new BigDecimal(list.get(i).getPrice()).compareTo(products.get(i).getPrice()) != 0) {
                return true;
            }
        }
        return false;
    }

    @Resource
    ProductStockMapper productStockMapper;

    /**
     * 判断库存是否足够
     * true 表示足够
     * false 表示不够
     */
    private boolean isStock(List<OrderItemRequestParam> orderItemRequestParams) {
        List<Long> ids = new ArrayList<>();
        orderItemRequestParams.forEach(orderItemRequestParam -> {
            ids.add(orderItemRequestParam.getProductId());
        });
        List<ProductStock> productStocks = productStockMapper.selectByProductIds(ids);
        // 判断长度是否一致  如果长度不一致  表示下架的商品
        for (int i = 0; i < productStocks.size(); i++) {
            if (productStocks.get(i).getTotal() < orderItemRequestParams.get(i).getQuantity()) {
                return false;
            }
        }
        return true;
    }


}
