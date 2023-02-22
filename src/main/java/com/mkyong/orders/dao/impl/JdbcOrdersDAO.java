package com.mkyong.orders.dao.impl;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcOrdersDAO implements OrdersDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Orders orders){

        String sql = "INSERT INTO orders (shop_id, create_date,modify_date) VALUES ( ?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders.getShop_id());
            ps.setDate(2, (Date) orders.getCreate_date());
            ps.setDate(3, (Date) orders.getModify_date());
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

    public Orders findByOrdersId(int orders_id){

        String sql = "SELECT * FROM orders WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders_id);
            Orders orders = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orders = new Orders(
                        rs.getInt("id"),
                        rs.getInt("Shop_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
            }
            rs.close();
            ps.close();
            return orders;
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