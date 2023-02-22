package com.mkyong.orders.dao.impl;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.orders.model.OrdersGoods;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcOrdersGoodsDAO implements OrdersGoodsDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(OrdersGoods ordersGoods){

        String sql = "INSERT INTO goods (orders_id,goods_id,goods_count,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ordersGoods.getOrders_id());
            ps.setInt(2, ordersGoods.getGoods_id());
            ps.setDouble(3, ordersGoods.getGoods_count());
            ps.setDate(4, (Date) ordersGoods.getCreate_date());
            ps.setDate(5, (Date) ordersGoods.getModify_date());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public OrdersGoods findByOrdersGoodsId(int orders_goods_id){

        String sql = "SELECT * FROM orders_goods WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders_goods_id);
            OrdersGoods ordersGoods = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ordersGoods = new OrdersGoods(
                        rs.getInt("id"),
                        rs.getInt("Orders_id"),
                        rs.getInt("Goods_id"),
                        rs.getDouble("Goods_count"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
            }
            rs.close();
            ps.close();
            return ordersGoods;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}