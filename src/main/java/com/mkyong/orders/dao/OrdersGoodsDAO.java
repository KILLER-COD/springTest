package com.mkyong.orders.dao;

import com.mkyong.orders.model.Orders;
import com.mkyong.orders.model.OrdersGoods;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersGoodsDAO {
   void insert(OrdersGoods ordersGoods);
   OrdersGoods findByOrdersGoodsId(int id);
    void update(OrdersGoods ordersGoods, int ordersGoodsId) throws SQLException;
    void update(int ordersId, int goodsId,double goodsCount,int ordersGoodsId) throws SQLException;
    void deleteHard(int ordersId) throws SQLException;
    void deleteSoft(int ordersId) throws SQLException;
    ArrayList<OrdersGoods> getAllOrdersGoods() throws SQLException;
    ArrayList<OrdersGoods> getAllDeletedOrdersGoods() throws SQLException;
}
