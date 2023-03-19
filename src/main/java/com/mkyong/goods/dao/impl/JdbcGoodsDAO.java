package com.mkyong.goods.dao.impl;

import com.mkyong.common.ColumnNames;
import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcGoodsDAO implements GoodsDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcGoodsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Goods goods, Connection conn) {
        String sql = "INSERT INTO goods (goods_name, goods_type,goods_price, product_id ,create_date,modify_date) VALUES ( ?, ?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                goods.getGoodsName(),
                goods.getGoodsType(),
                goods.getGoodsPrice(),
                goods.getProductId(),
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );
    }

    public Goods findByGoodsId(int goodsId) {
        String sql = "SELECT * FROM goods WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{goodsId}, new BeanPropertyRowMapper<>(Goods.class))
                .stream().findAny().orElse(null);
    }

    public void deleteSoft(int goodsId) {
        String sql = "UPDATE goods SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                goodsId
        );
    }

    public void deleteHard(int goodsId) {
        String sql = "DELETE FROM goods WHERE id = ?";
        jdbcTemplate.update(sql, goodsId);
    }

    public List<Goods> getAllGoods() {
        String sql = "SELECT * FROM goods WHERE delete_date IS NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Goods.class));
    }

    public List<Goods> getAllDeletedGoods() {
        String sql = "SELECT * FROM goods WHERE delete_date IS NOT NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Goods.class));
    }

    public void update(Goods goods, Connection conn) throws SQLException {
        String sql = "UPDATE goods SET goods_name = ? ,goods_type = ? ,goods_price = ? ,product_id = ?,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                goods.getGoodsName(),
                goods.getGoodsType(),
                goods.getGoodsPrice(),
                goods.getProductId(),
                new Date(System.currentTimeMillis()),
                goods.getId()
        );
    }


    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Goods getResultGoods(ResultSet resultSet) throws SQLException {
        Goods goods = new Goods(
                resultSet.getInt(ColumnNames.id),
                resultSet.getString(ColumnNames.goodsName),
                resultSet.getString(ColumnNames.goodsType),
                resultSet.getDouble(ColumnNames.goodsPrice),
                resultSet.getInt(ColumnNames.productId),
                resultSet.getDate(ColumnNames.createDate),
                resultSet.getDate(ColumnNames.modifyDate),
                resultSet.getDate(ColumnNames.deleteDate)
        );
        return goods;
    }

    public int plus(int n1, int n2) {
        if (n1 > 100) {
            return 10;
        }
        return n1 + n2;
    }


    //    public void update(String goodsName, String goodsType, double goodsPrice, int productId, int goodsId,Connection conn) throws SQLException {
//
//        Goods goods = findByGoodsId(goodsId);
//
//        if (goodsName != null) {
//            goods.setGoodsName(goodsName);
//        }
//        if (goodsType != null) {
//            goods.setGoodsType(goodsType);
//        }
//        if (goodsPrice > -1) {
//            goods.setGoodsPrice(goodsPrice);
//        }
//        if (productId > -1) {
//            goods.setProductId(productId);
//        }
//        update(goods, goodsId,conn);
//    }
}