CREATE TABLE t_order
(
    order_id                  int AUTO_INCREMENT PRIMARY KEY,
    order_sn                  varchar(64)    DEFAULT NULL COMMENT '订单编号',
    user_id                   int COMMENT '用户ID',
    `create_time`             datetime       DEFAULT NULL COMMENT '提交时间',
    `total_amount`            decimal(10, 2) DEFAULT NULL COMMENT '订单总金额',
    `pay_type`                int(1)         DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信 3->银联支付',
    `receiver_name`           varchar(100) NOT NULL COMMENT '收货人姓名',
    `receiver_phone`          varchar(32)  NOT NULL COMMENT '收货人电话',
    `receiver_detail_address` varchar(200)   DEFAULT NULL COMMENT '详细地址',
    `status`                  int(1)         DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单'
) COMMENT '订单表';


CREATE TABLE order_item
(
    id               bigint PRIMARY KEY AUTO_INCREMENT,
    order_id         bigint COMMENT '订单id',
    order_no         varchar(64) COMMENT '订单编号',
    product_id       bigint COMMENT '商品id',
    product_pic      varchar(500) COMMENT '商品图片',
    product_name     varchar(200) COMMENT '商品名称',
    product_price    decimal(10, 2) COMMENT '销售价格',
    product_quantity int COMMENT '购买数量'
) COMMENT '订单详情表';