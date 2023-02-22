package com.mkyong.orders.dao;

import com.mkyong.orders.model.OrdersGoods;

public interface OrdersGoodsDAO {
    public void insert(OrdersGoods ordersGoods);
    public OrdersGoods findByOrdersGoodsId(int id);
}
