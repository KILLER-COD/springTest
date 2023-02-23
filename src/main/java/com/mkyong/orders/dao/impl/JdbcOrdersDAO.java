package com.mkyong.orders.dao.impl;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

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
            ps.setInt(1, orders.getShopId());
            ps.setDate(2, orders.getCreateDate());
            ps.setDate(3, orders.getModifyDate());
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

    public void deleteSoft(int ordersId){
        String sql = "UPDATE orders SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,new Date(System.currentTimeMillis()));
            ps.setInt(2, ordersId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int ordersId){
        String sql = "DELETE FROM orders WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ordersId);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

    }

    public ArrayList<Orders> getAllOrders(){
        String sql = "SELECT * FROM orders WHERE delete_date IS NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Orders orders = null;
            ArrayList<Orders> ordersList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders = new Orders(
                        rs.getInt("id"),
                        rs.getInt("Shop_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                ordersList.add(orders);
            }
            rs.close();
            ps.close();
            return ordersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<Orders> getAllDeletedOrders(){
        String sql = "SELECT * FROM orders WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Orders orders = null;
            ArrayList<Orders> ordersList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders = new Orders(
                        rs.getInt("id"),
                        rs.getInt("Shop_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                ordersList.add(orders);
            }
            rs.close();
            ps.close();
            return ordersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void update(int shopId,int ordersId) throws SQLException {

        Orders orders =  findByOrdersId(ordersId);

        if (shopId > -1 ){
            orders.setShopId(shopId);
        }
        update(orders,ordersId);
    }

    public void update(Orders orders, int ordersId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE orders SET shop_id = ? ,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, orders.getShopId());
        ps.setInt(1, orders.getShopId());
        ps.setInt(2, ordersId);
        ps.executeUpdate();
        ps.close();

        closeConnection(conn);

    }

    public void closeConnection(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }
}