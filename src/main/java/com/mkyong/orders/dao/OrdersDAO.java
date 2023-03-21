package com.mkyong.orders.dao;

import com.mkyong.orders.model.Orders;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO {
    int insert(Orders orders, Connection conn);

    Orders findByOrdersId(int ordersId);

    void update(Orders orders, int ordersId) throws SQLException;

    //    void update(int shopId, int goodsId) throws SQLException;
    void deleteHard(int ordersId) throws SQLException;

    void deleteSoft(int ordersId) throws SQLException;

    List<Orders> getAllOrders() throws SQLException;

    List<Orders> getAllDeletedOrders() throws SQLException;

    void getSingleOrderInfo(int ordersId) throws SQLException;

    void getAllOrderInfo() throws SQLException;

    void getAllOrdersInfoByDate(Date date) throws SQLException;
}
