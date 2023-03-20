package com.mkyong.orders.dao;

import com.mkyong.orders.model.OrdersGoods;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrdersGoodsDAO {
    int insert(OrdersGoods ordersGoods, Connection conn);

    OrdersGoods findByOrdersGoodsId(int id);

    List<OrdersGoods> findByOrdersGoodsOrdersId(int id);

    void update(OrdersGoods ordersGoods, int ordersGoodsId) throws SQLException;

    void update(int ordersId, int goodsId, double goodsCount, int ordersGoodsId) throws SQLException;

    void deleteHard(int ordersId) throws SQLException;

    void deleteSoft(int ordersId) throws SQLException;

    ArrayList<OrdersGoods> getAllOrdersGoods() throws SQLException;

    ArrayList<OrdersGoods> getAllDeletedOrdersGoods() throws SQLException;
}
