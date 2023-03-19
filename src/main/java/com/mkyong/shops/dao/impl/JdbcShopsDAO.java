package com.mkyong.shops.dao.impl;

import com.mkyong.common.ColumnNames;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class JdbcShopsDAO implements ShopsDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcShopsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insert(Shops shops, Connection conn) {
        String sql = "INSERT INTO shops (shop_name, shop_address_id ,shop_info_id ,create_date ,modify_date) VALUES ( ?,?,?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, shops.getShopName());
                preparedStatement.setInt(2, shops.getShopAddressId());
                preparedStatement.setInt(3, shops.getShopInfoId());
                preparedStatement.setDate(4, shops.getCreateDate());
                preparedStatement.setDate(5, shops.getModifyDate());

                return preparedStatement;
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
        }, generatedKeyHolder);
        return generatedKeyHolder.getKey().intValue();
    }

    public Shops findByShopsId(int shopsId) {
        String sql = "SELECT * FROM shops WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{shopsId}, new BeanPropertyRowMapper<>(Shops.class))
                .stream().findAny().orElse(null);
    }

    public void deleteSoft(int shopsId) {
        String sql = "UPDATE shops SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                shopsId
        );
    }

    public void deleteHard(int shopsId) {
        String sql = "DELETE FROM shops WHERE id = ?";
        jdbcTemplate.query(sql, new Object[]{shopsId}, new BeanPropertyRowMapper<>(Shops.class));
    }

    public void update(Shops shops, Connection conn) throws SQLException {
        String sql = "UPDATE shops SET shop_name = ? ,shop_address_id = ? ,shop_info_id = ? ,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                shops.getShopName(),
                shops.getShopAddressId(),
                shops.getShopInfoId(),
                new Date(System.currentTimeMillis()),
                shops.getId()
        );
    }

    public List<Shops> getAllShops() {
        String sql = "SELECT * FROM shops WHERE ISNULL(delete_date)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shops.class));
    }

    public List<Shops> getAllDeletedShops() {
        String sql = "SELECT * FROM shops WHERE !ISNULL(delete_date)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shops.class));
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Shops getResultShop(ResultSet resultSet) throws SQLException {
        Shops shop = new Shops(
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