package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopPersonDataDAO;
import com.mkyong.shops.model.ShopPersonData;
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
public class JdbcShopPersonDataDAO implements ShopPersonDataDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int insert(ShopPersonData shopPersonData, Connection conn) {
        String sql = "INSERT INTO shop_person_data (person_name, person_email,shop_id,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, shopPersonData.getPersonName());
            preparedStatement.setString(2, shopPersonData.getPersonEmail());
            preparedStatement.setInt(3, shopPersonData.getShopId());
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setDate(5, new Date(System.currentTimeMillis()));

            return preparedStatement;

        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().intValue();
    }

    @Override
    public Optional<ShopPersonData> findById(int shopPersonDataId) {
        String sql = "SELECT * FROM shop_person_data WHERE id = ? && delete_date IS NULL";
        return jdbcTemplate.query(sql, new Object[]{shopPersonDataId}, new BeanPropertyRowMapper<>(ShopPersonData.class))
                .stream().findAny();
    }

    @Override
    public List<ShopPersonData> findAllByIdShopId(int shopId) {
        String sql = "SELECT * FROM shop_person_data WHERE shop_id = ? && delete_date IS NULL";
        return jdbcTemplate.query(sql, new Object[]{shopId}, new BeanPropertyRowMapper<>(ShopPersonData.class));
    }

    @Override
    public void update(ShopPersonData shopPersonData, Connection conn) throws SQLException {
        String sql = "UPDATE shop_person_data SET person_name = ? ,person_email = ? ,shop_id = ?,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                shopPersonData.getPersonName(),
                shopPersonData.getPersonEmail(),
                shopPersonData.getShopId(),
                new Date(System.currentTimeMillis()),
                shopPersonData.getId()
        );
    }

    @Override
    public void deleteHard(int shopPersonDataId) throws SQLException {
        String sql = "DELETE FROM shop_person_data  WHERE id = ?";
        jdbcTemplate.update(sql,
                shopPersonDataId
        );
    }

    @Override
    public void deleteSoft(int shopPersonDataId) throws SQLException {
        String sql = "UPDATE shop_person_data SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                shopPersonDataId
        );
    }

    @Override
    public List<ShopPersonData> getAllShopPersonData() throws SQLException {
        String sql = "SELECT * FROM shop_person_data WHERE delete_date IS NULL  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopPersonData.class));
    }

    @Override
    public List<ShopPersonData> getAllDeletedShopPersonData() throws SQLException {
        String sql = "SELECT * FROM shop_person_data WHERE delete_date IS NOT NULL  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopPersonData.class));
    }
}
