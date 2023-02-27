package com.mkyong.orders.dao;

import com.mkyong.goods.model.Goods;
import com.mkyong.orders.model.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersDAO {
    int insert(Orders orders, Connection conn);
    Orders findByOrdersId(int ordersId);
    void update(Orders orders, int ordersId) throws SQLException;
    void update(int shopId, int goodsId) throws SQLException;
    void deleteHard(int ordersId) throws SQLException;
    void deleteSoft(int ordersId) throws SQLException;
    ArrayList<Orders> getAllOrders() throws SQLException;
    ArrayList<Orders> getAllDeletedOrders() throws SQLException;
}
