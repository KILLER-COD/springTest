package com.mkyong.methods;

import com.mkyong.orders.model.AllOrdersData;
import com.mkyong.orders.model.OrderShopInfo;
import com.mkyong.shops.model.GetShopAllData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
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
        String sql = "SELECT o.id,o.shopId,og.orders_id,goods_name,goods_count,goods_price,o.create_date " +
                "FROM orders_goods og " +
                "join orders o on og.orders_id = o.id && og.create_date = o.create_date " +
                "join goods g on og.goods_id = g.id " +
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
        jdbcTemplate.query(sql, new Object[]{ordersId}, new BeanPropertyRowMapper<>(OrderShopInfo.class));

        return allOrdersData;
    }

    public List<OrderShopInfo> allOrdersShopsInfo() {
        String sql = "SELECT o.id,shop_name,address,city,o.create_date" +
                " FROM orders o" +
                " join shops s on o.shop_id = s.id " +
                " join address a on s.shop_address_id = a.id " +
                " WHERE o.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderShopInfo.class));
    }

    public List<OrderShopInfo> allShopsInformation() {
        String sql = "SELECT s.id,shop_name,address,city,s.create_date " +
                " FROM shops s " +
                " join address a on s.shop_address_id = a.id " +
                " WHERE s.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderShopInfo.class));
    }

    public OrderShopInfo getSingleOrderInformation(int ordersId) throws SQLException {
        String sql = "SELECT o.id,o.shopId,shop_name,address,city,o.create_date " +
                " FROM orders o " +
                " join shops s on o.shop_id = s.id " +
                " join address a on s.shop_address_id = a.id " +
                " WHERE o.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new Object[]{ordersId}, new BeanPropertyRowMapper<>(OrderShopInfo.class))
                .stream().findAny().orElse(null);
    }

    public GetShopAllData getSingleShopData(int shopId) {
        String sql = "SELECT s.id ,s.shop_name,a.address as shopAddress,a.city as shopCity,s.shop_info_id as shopInfoId,sInfo.shop_owner,sInfo.hvhh,adressSInfo.address as shopInfoAddress,adressSInfo.city as shopInfoCity, s.create_date ,s.modify_date" +
                "               FROM shops s  " +
                "               join shops_info sInfo on s.shop_info_id = sInfo.id " +
                "               join address a on s.shop_address_id= a.id " +
                "               join address adressSInfo on sInfo.address_id= adressSInfo.id " +
                "               WHERE s.id = ? && s.delete_date IS NULL ;";
        return jdbcTemplate.query(sql, new Object[]{shopId}, new BeanPropertyRowMapper<>(GetShopAllData.class)).stream().findAny().orElse(null);
    }

    public List<GetShopAllData> getAllShopData() {
        String sql = "SELECT s.id ,s.shop_name,a.address as shopAddress,a.city as shopCity,sInfo.shop_owner,sInfo.hvhh,adressSInfo.address as shopInfoAddress,adressSInfo.city as shopInfoCity, s.create_date ,s.modify_date" +
                "               FROM shops s  " +
                "               join shops_info sInfo on s.shop_info_id = sInfo.id " +
                "               join address a on s.shop_address_id= a.id " +
                "               join address adressSInfo on sInfo.address_id= adressSInfo.id " +
                "               WHERE s.delete_date IS NULL ;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetShopAllData.class));
    }

}
