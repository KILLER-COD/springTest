package com.mkyong.orders.dao.impl;

import com.mkyong.common.ColumnNames;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class JdbcOrdersDAO implements OrdersDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcOrdersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insert(Orders orders, Connection conn) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO orders (shop_id, create_date,modify_date) VALUES ( ?, ?, ?)";
        return jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orders.getShopId());
            ps.setDate(2, orders.getCreateDate());
            ps.setDate(3, orders.getModifyDate());
            return ps;
        }, generatedKeyHolder);
    }

    public void update(Orders orders, int ordersId) throws SQLException {
        String sql = "UPDATE orders SET shop_id = ? ,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orders.getShopId());
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setInt(3, ordersId);
            return ps;
        });
    }

    public Orders findByOrdersId(int orders_id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{orders_id}, new BeanPropertyRowMapper<>(Orders.class))
                .stream().findAny().orElse(null);
    }

    public void deleteSoft(int ordersId) {
        String sql = "UPDATE orders SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                ordersId
        );
    }

    public void deleteHard(int ordersId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        jdbcTemplate.update(sql, ordersId);
    }

    public List<Orders> getAllOrders() {
        String sql = "SELECT * FROM orders WHERE delete_date IS NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Orders.class));
    }

    public List<Orders> getAllDeletedOrders() {
        String sql = "SELECT * FROM orders WHERE delete_date IS NOT NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Orders.class));
    }


    public void getSingleOrderInfo(int ordersId) throws SQLException {
        String sql = "SELECT orders_id,shop_name,goods_name,goods_count,goods_price,o.create_date" +
                " FROM orders_goods og" +
                " join orders o on og.orders_id = o.id " +
                " join shops s on o.shop_id = s.id " +
                " join goods g on og.goods_id = g.id" +
                " WHERE og.orders_id = ? && og.delete_date IS NULL; ";

        Connection conn = dataSource.getConnection();
        PreparedStatement ps;

        ps = conn.prepareStatement(sql);
        ps.setInt(1, ordersId);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
//        OrdersInfo o = new OrdersInfo();
        while (rs.next()) {
            System.out.println(
                    "Id = " + rs.getInt("orders_id") +
                            "| Shop Name = " + rs.getString("shop_name") +
                            "| Goods Name = " + rs.getString("goods_name") +
                            "| Goods Count = " + rs.getDouble("goods_count") +
                            "| Goods Price = " + rs.getDouble("goods_price") +
                            "| Order Create = " + rs.getDate("create_date")
            );
        }
        ps.close();

        closeConnection(conn);
    }

    public void getAllOrderInfo() throws SQLException {
        String sql = "SELECT orders_id,shop_name,goods_name,goods_count,goods_price ,o.create_date" +
                " FROM orders_goods og" +
                " join orders o on og.orders_id = o.id " +
                " join shops s on o.shop_id = s.id " +
                " join goods g on og.goods_id = g.id" +
                " WHERE og.delete_date IS NULL;";

        Connection conn = dataSource.getConnection();
        ;
        PreparedStatement ps;

        ps = conn.prepareStatement(sql);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
//        OrdersInfo o = new OrdersInfo();
        while (rs.next()) {
            System.out.println(
                    "Id = " + rs.getInt("orders_id") +
                            "| Shop Name = " + rs.getString("shop_name") +
                            "| Goods Name = " + rs.getString("goods_name") +
                            "| Goods Count = " + rs.getDouble("goods_count") +
                            "| Goods Price = " + rs.getDouble("goods_price") +
                            "| Order Create = " + rs.getDate("create_date") +
                            "\n-------------------------------------------------------"
            );
        }
        ps.close();

        closeConnection(conn);
    }

    public void getAllOrdersInfoByDate(Date date) throws SQLException {
        String sql = "SELECT orders_id,s.id,shop_name,goods_name,goods_count,goods_price,o.create_date,og.delete_date " +
                " FROM orders_goods og" +
                " join orders o on og.orders_id = o.id && og.create_date = o.create_date" +
                " join shops s on o.shop_id = s.id" +
                " join goods g on og.goods_id = g.id" +
                " WHERE o.create_date = ? && og.delete_date IS NULL;";

        Connection conn = dataSource.getConnection();
        ;
        PreparedStatement ps;

        ps = conn.prepareStatement(sql);
        ps.setDate(1, date);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
//        OrdersInfo o = new OrdersInfo();
        while (rs.next()) {
            System.out.println(
                    "Id = " + rs.getInt("orders_id") +
                            "| Shop Name = " + rs.getString("shop_name") +
                            "| Goods Name = " + rs.getString("goods_name") +
                            "| Goods Count = " + rs.getDouble("goods_count") +
                            "| Goods Price = " + rs.getDouble("goods_price") +
                            "| Orders Create = " + rs.getDate("o.create_date")
            );
        }
        ps.close();

        closeConnection(conn);
    }


    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Don't close db connection");
            }
        }
    }

    public Orders getResultOrder(ResultSet resultSet) throws SQLException {
        Orders orders = new Orders(
                resultSet.getInt(ColumnNames.id),
                resultSet.getInt(ColumnNames.shopId),
                resultSet.getDate(ColumnNames.createDate),
                resultSet.getDate(ColumnNames.modifyDate),
                resultSet.getDate(ColumnNames.deleteDate)
        );
        return orders;
    }
}