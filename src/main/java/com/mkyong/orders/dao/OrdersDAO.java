package com.mkyong.orders.dao;

import com.mkyong.orders.model.Orders;

public interface OrdersDAO {
    public void insert(Orders orders);
    public void findByOrdersId(int orders_id);
}
