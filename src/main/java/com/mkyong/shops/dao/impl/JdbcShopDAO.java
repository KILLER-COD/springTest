package com.mkyong.shops.dao.impl;

import com.mkyong.common.ColumnNames;
import com.mkyong.shops.dao.ShopDAO;
import com.mkyong.shops.model.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcShopDAO implements ShopDAO {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public int insert(Shop shop, Connection conn) {
        String sql = "INSERT INTO shop (shop_name, shop_address_id ,shop_info_id ,create_date ,modify_date) VALUES ( ?,?,?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, shop.getShopName());
                preparedStatement.setInt(2, shop.getShopAddressId());
                preparedStatement.setInt(3, shop.getShopInfoId());
                preparedStatement.setDate(4, shop.getCreateDate());
                preparedStatement.setDate(5, shop.getModifyDate());

                return preparedStatement;
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
        }, generatedKeyHolder);
        return generatedKeyHolder.getKey().intValue();
    }

    public Shop findByShopsId(int shopsId) {
        String sql = "SELECT * FROM shop WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{shopsId}, new BeanPropertyRowMapper<>(Shop.class))
                .stream().findAny().orElse(null);
    }

    public void deleteSoft(int shopsId) {
        String sql = "UPDATE shop SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                shopsId
        );
    }

    public void deleteHard(int shopsId) {
        String sql = "DELETE FROM shop WHERE id = ?";
        jdbcTemplate.query(sql, new Object[]{shopsId}, new BeanPropertyRowMapper<>(Shop.class));
    }

    public void update(Shop shop, Connection conn) throws SQLException {
        String sql = "UPDATE shop SET shop_name = ? ,shop_address_id = ? ,shop_info_id = ? ,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                shop.getShopName(),
                shop.getShopAddressId(),
                shop.getShopInfoId(),
                new Date(System.currentTimeMillis()),
                shop.getId()
        );
    }

    public List<Shop> getAllShops() {
        String sql = "SELECT * FROM shop WHERE ISNULL(delete_date)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shop.class));
    }

    public List<Shop> getAllDeletedShops() {
        String sql = "SELECT * FROM shop WHERE !ISNULL(delete_date)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shop.class));
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Shop getResultShop(ResultSet resultSet) throws SQLException {
        Shop shop = new Shop(
                resultSet.getInt(ColumnNames.id),
                resultSet.getString(ColumnNames.shopName),
                resultSet.getInt(ColumnNames.shopAddressId),
                resultSet.getInt(ColumnNames.shopInfoId),
                resultSet.getDate(ColumnNames.createDate),
                resultSet.getDate(ColumnNames.modifyDate),
                resultSet.getDate(ColumnNames.deleteDate)
        );
        return shop;
    }


}