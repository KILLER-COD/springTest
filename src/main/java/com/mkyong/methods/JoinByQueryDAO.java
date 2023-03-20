package com.mkyong.methods;

import com.mkyong.orders.model.AllOrdersData;
import com.mkyong.orders.model.OrdersShopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JoinByQueryDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Autowired
    public JoinByQueryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AllOrdersData ordersGoodsInfoByOrdersId(int ordersId) {
        AllOrdersData allOrdersData = new AllOrdersData();
        String sql = "SELECT orders_id,goods_name,goods_count,goods_price,o.create_date\n" +
                "FROM orders_goods og " +
                "join orders o on og.orders_id = o.id && og.create_date = o.create_date " +
                "join goods g on og.goods_id = g.id" +
                "WHERE og.orders_id = 2 && og.delete_date IS NULL;";
//        jdbcTemplate.query(sql,new BeanPropertyRowMapper<>())

        return allOrdersData;
    }

    public AllOrdersData findOrdersShopInfoByOrdersId(int ordersId) {
        AllOrdersData allOrdersData = new AllOrdersData();
        String sql = "SELECT o.id,shop_name,address,city,o.create_date " +
                "FROM orders o " +
                "join shops s on o.shop_id = s.id " +
                "join address a on s.shop_address_id = a.id " +
                "WHERE o.id = ? && o.delete_date IS NULL;";
        jdbcTemplate.query(sql, new Object[]{ordersId}, new BeanPropertyRowMapper<>(OrdersShopInfo.class));

        return allOrdersData;
    }

    public List<OrdersShopInfo> allOrdersShopsInfo() {
        String sql = "SELECT o.id,shop_name,address,city,o.create_date" +
                " FROM orders o" +
                " join shops s on o.shop_id = s.id " +
                " join address a on s.shop_address_id = a.id " +
                " WHERE o.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrdersShopInfo.class));
    }

    public List<OrdersShopInfo> allShopsInformation() {
        String sql = "SELECT s.id,shop_name,address,city,s.create_date" +
                " FROM shops s" +
                " join address a on s.shop_address_id = a.id " +
                " WHERE s.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrdersShopInfo.class));
    }

}
