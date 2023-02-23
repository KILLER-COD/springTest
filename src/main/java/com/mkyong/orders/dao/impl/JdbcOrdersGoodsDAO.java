package com.mkyong.orders.dao.impl;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.orders.model.OrdersGoods;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class JdbcOrdersGoodsDAO implements OrdersGoodsDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(OrdersGoods ordersGoods){

        String sql = "INSERT INTO orders_goods (orders_id,goods_id,goods_count,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ordersGoods.getOrdersId());
            ps.setInt(2, ordersGoods.getGoodsId());
            ps.setDouble(3, ordersGoods.getGoodsCount());
            ps.setDate(4,  ordersGoods.getCreateDate());
            ps.setDate(5,  ordersGoods.getModifyDate());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            closeConnection(conn);
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
            closeConnection(conn);
        }
    }

    public void deleteSoft(int ordersId){
        String sql = "UPDATE orders_goods SET delete_date = ? WHERE id = ?";
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
        String sql = "DELETE FROM orders_goods WHERE id = ?";
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

    public ArrayList<OrdersGoods> getAllOrdersGoods(){
        String sql = "SELECT * FROM orders_goods WHERE delete_date IS NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            OrdersGoods ordersGoods = null;
            ArrayList<OrdersGoods> ordersGoodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordersGoods = new OrdersGoods(
                        rs.getInt("id"),
                        rs.getInt("Orders_id"),
                        rs.getInt("Goods_id"),
                        rs.getInt("Goods_Count"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                ordersGoodsList.add(ordersGoods);
            }
            rs.close();
            ps.close();
            return ordersGoodsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<OrdersGoods> getAllDeletedOrdersGoods(){
        String sql = "SELECT * FROM orders_goods WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            OrdersGoods ordersGoods = null;
            ArrayList<OrdersGoods> ordersGoodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordersGoods = new OrdersGoods(
                        rs.getInt("id"),
                        rs.getInt("Orders_id"),
                        rs.getInt("Goods_id"),
                        rs.getInt("Goods_Count"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                ordersGoodsList.add(ordersGoods);
            }
            rs.close();
            ps.close();
            return ordersGoodsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void update(int ordersId,int goodsId,double goodsCount,int ordersGoodsId) throws SQLException {

        OrdersGoods ordersGoods =  findByOrdersGoodsId(ordersGoodsId);

        if (ordersId > -1 && goodsId == -1 && goodsCount == -1 ){
            ordersGoods.setOrdersId(ordersId);
        } else if (ordersId == -1 && goodsId > -1 && goodsCount == -1  ) {
            ordersGoods.setGoodsId(goodsId);
        } else if (ordersId == -1 && goodsId == -1 && goodsCount > -1  ) {
            ordersGoods.setGoodsCount(goodsCount);
        } else {
           ordersGoods.setOrdersId(ordersId);
           ordersGoods.setGoodsId(goodsId);
           ordersGoods.setGoodsCount(goodsCount);
        }
        update(ordersGoods,ordersGoodsId);
    }

    public void update(OrdersGoods ordersGoods, int ordersGoodsId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE orders_goods SET orders_id = ?,goods_id = ? , goods_count = ? ,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, ordersGoods.getOrdersId());
        ps.setInt(2,ordersGoods.getGoodsId());
        ps.setDouble(3,ordersGoods.getGoodsCount());
        ps.setDate(4, new Date(System.currentTimeMillis()));
        ps.setInt(5, ordersGoodsId);
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