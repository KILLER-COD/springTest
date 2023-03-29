package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopPersonContactDAO;
import com.mkyong.shops.model.ShopPersonContact;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcShopPersonContactDAO implements ShopPersonContactDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int insert(ShopPersonContact personContact, Connection conn) {
        String sql = "INSERT INTO shop_person_contact (phone,shop_person_data_id,create_date,modify_date) VALUES ( ?,?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            preparedStatement.setString(1, personContact.getPhone());
            preparedStatement.setInt(2, personContact.getShopPersonDataId());
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            return preparedStatement;
        }, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
    }

    @Override
    public Optional<ShopPersonContact> findById(int personContactId) {
        String sql = "SELECT * FROM shop_person_contact WHERE id = ? && delete_date IS NULL";
        return jdbcTemplate.query(sql, new Object[]{personContactId}, new BeanPropertyRowMapper<>(ShopPersonContact.class))
                .stream().findAny();
    }

    @Override
    public List<ShopPersonContact> findByShopPersonDataId(int personContactId) {
        String sql = "SELECT * FROM shop_person_contact WHERE shop_person_data_id = ? && delete_date IS NULL";
        return jdbcTemplate.query(sql, new Object[]{personContactId}, new BeanPropertyRowMapper<>(ShopPersonContact.class));
    }

    @Override
    public void update(ShopPersonContact personContact, Connection conn) throws SQLException {
        String sql = "UPDATE shop_person_contact SET phone = ? ,shop_person_data_id = ?,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                personContact.getPhone(),
                personContact.getShopPersonDataId(),
                new Date(System.currentTimeMillis()),
                personContact.getId()
        );
    }

    @Override
    public void deleteHard(int personContactId) throws SQLException {
        String sql = "DELETE FROM shop_person_contact  WHERE id = ?";
        jdbcTemplate.update(sql,
                personContactId
        );
    }

    @Override
    public void deleteSoft(int personContactId) throws SQLException {
        String sql = "UPDATE shop_person_contact SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                personContactId
        );
    }

    @Override
    public void deleteSoftByShopPersonDataId(int personDataId) throws SQLException {
        String sql = "UPDATE shop_person_contact SET delete_date = ? WHERE shop_person_data_id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                personDataId
        );
    }

    @Override
    public List<ShopPersonContact> getAllShopPersonContact() throws SQLException {
        String sql = "SELECT * FROM shop_person_contact WHERE delete_date IS NULL  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopPersonContact.class));
    }

    @Override
    public List<ShopPersonContact> getAllDeletedShopPersonContacts() throws SQLException {
        String sql = "SELECT * FROM shop_person_contact WHERE delete_date IS NOT NULL  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopPersonContact.class));
    }
}
