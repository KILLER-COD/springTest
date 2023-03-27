package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopContactingInfoDAO;
import com.mkyong.shops.model.ShopContactingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcShopContactingInfoDAO implements ShopContactingInfoDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int insert(ShopContactingInfo contactingInfo, Connection conn) {
        String sql = "INSERT INTO shop_contact_info (name, email,phone1, phone2 ,shop_info_id,create_date,modify_date) VALUES ( ?, ?,?,?,?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, contactingInfo.getName());
            preparedStatement.setString(2, contactingInfo.getEmail());
            preparedStatement.setString(3, contactingInfo.getPhone1());
            preparedStatement.setString(4, contactingInfo.getPhone2());
            preparedStatement.setInt(5, contactingInfo.getShopInfoId());
            preparedStatement.setDate(6, new Date(System.currentTimeMillis()));
            preparedStatement.setDate(7, new Date(System.currentTimeMillis()));

            return preparedStatement;

        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().intValue();
    }

    @Override
    public Optional<ShopContactingInfo> findById(int contactingInfoId) {
        String sql = "SELECT * FROM shop_contact_info WHERE id = ? && delete_date IS NULL";
        return jdbcTemplate.query(sql, new Object[]{contactingInfoId}, new BeanPropertyRowMapper<>(ShopContactingInfo.class))
                .stream().findAny();
    }

    @Override
    public void update(ShopContactingInfo contactingInfo, Connection conn) throws SQLException {
        String sql = "UPDATE shop_contact_info SET name = ? ,email = ? ,phone1 = ?,phone2 = ? ,shop_info_id = ?,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                contactingInfo.getName(),
                contactingInfo.getEmail(),
                contactingInfo.getPhone1(),
                contactingInfo.getPhone2(),
                contactingInfo.getShopInfoId(),
                new Date(System.currentTimeMillis()),
                contactingInfo.getId()
        );
    }

    @Override
    public void deleteHard(int contactingInfoId) throws SQLException {
        String sql = "DELETE FROM shop_contact_info  WHERE id = ?";
        jdbcTemplate.update(sql,
                contactingInfoId
        );
    }

    @Override
    public void deleteSoft(int contactingInfoId) throws SQLException {
        String sql = "UPDATE shop_contact_info SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                contactingInfoId
        );
    }

    @Override
    public List<ShopContactingInfo> getAllShopContactingInfo() throws SQLException {
        String sql = "SELECT * FROM shop_contact_info WHERE delete_date IS NULL  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopContactingInfo.class));
    }

    @Override
    public List<ShopContactingInfo> getAllDeletedShops() throws SQLException {
        String sql = "SELECT * FROM shop_contact_info WHERE delete_date IS NOT NULL  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopContactingInfo.class));
    }
}
