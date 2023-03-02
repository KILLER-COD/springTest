package com.mkyong.orders.dao.impl;

import com.mkyong.common.ColumnNames;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.orders.model.OrdersGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class JdbcOrdersGoodsDAO implements OrdersGoodsDAO {
    @Autowired
    private DataSource dataSource;

    public int insert(OrdersGoods ordersGoods, Connection conn) {

        String sql = "INSERT INTO orders_goods (orders_id,goods_id,goods_count,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ordersGoods.getOrdersId());
            ps.setInt(2, ordersGoods.getGoodsId());
            ps.setDouble(3, ordersGoods.getGoodsCount());
            ps.setDate(4, ordersGoods.getCreateDate());
            ps.setDate(5, ordersGoods.getModifyDate());
            ps.executeUpdate();
            int ordersGoodsId = -1;
            ResultSet getGenarateKey = ps.getGeneratedKeys();
            if (getGenarateKey.next()) {
                ordersGoodsId = getGenarateKey.getInt(1);
            }
            ps.close();
            return ordersGoodsId;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
//            closeConnection(conn);
        }
    }

    public OrdersGoods findByOrdersGoodsId(int orders_goods_id) {

        String sql = "SELECT * FROM orders_goods WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders_goods_id);
            OrdersGoods ordersGoods = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ordersGoods = getResultOrdersGoods(rs);
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

    public void deleteSoft(int ordersId) {
        String sql = "UPDATE orders_goods SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setInt(2, ordersId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int ordersId) {
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

    public ArrayList<OrdersGoods> getAllOrdersGoods() {
        String sql = "SELECT * FROM orders_goods WHERE delete_date IS NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<OrdersGoods> ordersGoodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordersGoodsList.add(getResultOrdersGoods(rs));
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

    public ArrayList<OrdersGoods> getAllDeletedOrdersGoods() {
        String sql = "SELECT * FROM orders_goods WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<OrdersGoods> ordersGoodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordersGoodsList.add(getResultOrdersGoods(rs));
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

    public void update(int ordersId, int goodsId, double goodsCount, int ordersGoodsId) throws SQLException {

        OrdersGoods ordersGoods = findByOrdersGoodsId(ordersGoodsId);

        if (ordersId > -1 && goodsId == -1 && goodsCount == -1) {
            ordersGoods.setOrdersId(ordersId);
        } else if (ordersId == -1 && goodsId > -1 && goodsCount == -1) {
            ordersGoods.setGoodsId(goodsId);
        } else if (ordersId == -1 && goodsId == -1 && goodsCount > -1) {
            ordersGoods.setGoodsCount(goodsCount);
        } else {
            ordersGoods.setOrdersId(ordersId);
            ordersGoods.setGoodsId(goodsId);
            ordersGoods.setGoodsCount(goodsCount);
        }
        update(ordersGoods, ordersGoodsId);
    }

    public void update(OrdersGoods ordersGoods, int ordersGoodsId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE orders_goods SET orders_id = ?,goods_id = ? , goods_count = ? ,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, ordersGoods.getOrdersId());
        ps.setInt(2, ordersGoods.getGoodsId());
        ps.setDouble(3, ordersGoods.getGoodsCount());
        ps.setDate(4, new Date(System.currentTimeMillis()));
        ps.setInt(5, ordersGoodsId);
        ps.executeUpdate();
        ps.close();

        closeConnection(conn);

    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public OrdersGoods getResultOrdersGoods(ResultSet resultSet) throws SQLException {
        OrdersGoods ordersGoods = new OrdersGoods(
                resultSet.getInt(ColumnNames.id),
                resultSet.getInt(ColumnNames.ordersId),
                resultSet.getInt(ColumnNames.goodsId),
                resultSet.getDouble(ColumnNames.goodsCount),
                resultSet.getDate(ColumnNames.createDate),
                resultSet.getDate(ColumnNames.modifyDate),
                resultSet.getDate(ColumnNames.deleteDate)
        );
        return ordersGoods;
    }
}