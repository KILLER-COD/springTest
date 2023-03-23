package com.mkyong.methods;

import com.mkyong.goods.model.GetAllGoodsData;
import com.mkyong.orders.model.GetAllOrdersGoodsList;
import com.mkyong.orders.model.OrderShopInfo;
import com.mkyong.shops.model.GetShopAllData;
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


    public List<OrderShopInfo> allShopsInformation() {
        String sql = "SELECT s.id,shop_name,address,city,s.create_date as createDate" +
                " FROM shops s " +
                " join address a on s.shop_address_id = a.id " +
                " WHERE s.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderShopInfo.class));
    }

    public GetShopAllData getSingleShopData(int shopId) {
        String sql = "SELECT s.id ,s.shop_name,a.address as shopAddress,a.city as shopCity,s.shop_info_id as shopInfoId,sInfo.shop_owner,sInfo.hvhh,adressSInfo.address as shopInfoAddress,adressSInfo.city as shopInfoCity, s.create_date as createDate,s.modify_date as modifyDate" +
                "               FROM shops s  " +
                "               join shops_info sInfo on s.shop_info_id = sInfo.id " +
                "               join address a on s.shop_address_id= a.id " +
                "               join address adressSInfo on sInfo.address_id= adressSInfo.id " +
                "               WHERE s.id = ? && s.delete_date IS NULL ;";
        return jdbcTemplate.query(sql, new Object[]{shopId}, new BeanPropertyRowMapper<>(GetShopAllData.class)).stream().findAny().orElse(null);
    }

    public List<GetShopAllData> getAllShopData() {
        String sql = "SELECT s.id ,s.shop_name,a.address as shopAddress,a.city as shopCity,sInfo.shop_owner,sInfo.hvhh,adressSInfo.address as shopInfoAddress,adressSInfo.city as shopInfoCity, s.create_date as createDate,s.modify_date as modifyDate" +
                "               FROM shops s  " +
                "               join shops_info sInfo on s.shop_info_id = sInfo.id " +
                "               join address a on s.shop_address_id= a.id " +
                "               join address adressSInfo on sInfo.address_id= adressSInfo.id " +
                "               WHERE s.delete_date IS NULL ;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetShopAllData.class));
    }

    public List<GetAllOrdersGoodsList> getOrderGoodsInformation(int ordersId) {
        String sql = "SELECT o.id,g.id as goodsId,goods_name as goodsName,goods_count as goodsCount,goods_type as goodsType,goods_price as goodsPrice,og.create_date as createDate,og.modify_date as modifyDate " +
                "                FROM orders o " +
                "                join orders_goods og on o.id = og.orders_id && o.create_date = og.create_date" +
                "                join goods g on og.goods_id = g.id " +
                "                WHERE og.orders_id = ? && og.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new Object[]{ordersId}, new BeanPropertyRowMapper<>(GetAllOrdersGoodsList.class));
    }


    public List<GetAllGoodsData> getAllGoodsData() {
        String sql = "SELECT g.id ,goods_name as goodsName,goods_type as goodsType,goods_price as goodsPrice,product_name as productName,product_type as productType,g.create_date as createDate,g.modify_date as modifyDate" +
                "                FROM goods g" +
                "                join product p on g.product_id = p.id" +
                "                WHERE g.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetAllGoodsData.class));
    }

    public GetAllGoodsData getSingleGoodsData(int goodsId) {
        String sql = "SELECT g.id ,goods_name as goodsName,goods_type as goodsType,goods_price as goodsPrice,product_name as productName,product_type as productType,g.create_date as createDate,g.modify_date as modifyDate" +
                "                FROM goods g" +
                "                join product p on g.product_id = p.id" +
                "                WHERE g.id = ? && g.delete_date IS NULL;";
        return jdbcTemplate.query(sql, new Object[]{goodsId}, new BeanPropertyRowMapper<>(GetAllGoodsData.class))
                .stream()
                .findAny()
                .orElseThrow(null);
    }
}


//    public AllOrdersData ordersGoodsInfoByOrdersId(int ordersId) {
//        AllOrdersData allOrdersData = new AllOrdersData();
//        String sql = "SELECT o.id,o.shopId,og.orders_id,goods_name,goods_count,goods_price,o.create_date as createDate" +
//                "FROM orders_goods og " +
//                "join orders o on og.orders_id = o.id && og.create_date = o.create_date " +
//                "join goods g on og.goods_id = g.id " +
//                "WHERE og.orders_id = 2 && og.delete_date IS NULL;";
////        jdbcTemplate.query(sql,new BeanPropertyRowMapper<>())
//
//        return allOrdersData;
//    }
//
//    public AllOrdersData findOrdersShopInfoByOrdersId(int ordersId) {
//        AllOrdersData allOrdersData = new AllOrdersData();
//        String sql = "SELECT o.id,shop_name,address,city,o.create_date as createDate" +
//                "FROM orders o " +
//                "join shops s on o.shop_id = s.id " +
//                "join address a on s.shop_address_id = a.id " +
//                "WHERE o.id = ? && o.delete_date IS NULL;";
//        jdbcTemplate.query(sql, new Object[]{ordersId}, new BeanPropertyRowMapper<>(OrderShopInfo.class));
//
//        return allOrdersData;
//    }
//
//    public List<OrderShopInfo> allOrdersShopsInfo() {
//        String sql = "SELECT o.id,shop_name,address,city,o.create_date as createDate" +
//                " FROM orders o" +
//                " join shops s on o.shop_id = s.id " +
//                " join address a on s.shop_address_id = a.id " +
//                " WHERE o.delete_date IS NULL;";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderShopInfo.class));
//    }
//
//    public OrderShopInfo getSingleOrderShopInformation(int ordersId) throws SQLException {
//        String sql = "SELECT o.id,o.shopId,shop_name,address,city,o.create_date as createDate" +
//                " FROM orders o " +
//                " join shops s on o.shop_id = s.id " +
//                " join address a on s.shop_address_id = a.id " +
//                " WHERE o.delete_date IS NULL;";
//        return jdbcTemplate.query(sql, new Object[]{ordersId}, new BeanPropertyRowMapper<>(OrderShopInfo.class))
//                .stream().findAny().orElse(null);
//    }