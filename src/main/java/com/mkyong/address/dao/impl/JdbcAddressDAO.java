package com.mkyong.address.dao.impl;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class JdbcAddressDAO implements AddressDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAddressDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AddressCountByCity> findCountCity() {
        String sql = "SELECT COUNT(id) count, city FROM address WHERE delete_date IS NULL GROUP BY city;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AddressCountByCity.class));
    }

    public List<AddressCountByCity> findCountCity(Integer minCountShopInCity) {
        String sql = "SELECT COUNT(id) cnt,BINARY city FROM address WHERE delete_date IS NULL GROUP BY BINARY city HAVING COUNT(id) > ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AddressCountByCity.class));
    }

    public void deleteSoft(int addressId) {
        String sql = "UPDATE address SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                addressId
        );
    }

    public void deleteHard(int addressId) {
        String sql = "DELETE FROM address WHERE id = ?";
        jdbcTemplate.update(sql, addressId);
    }

    public int insert(Address address) {
        String sql = "INSERT  INTO address (address, city,create_date,modify_date) VALUES ( ?, ?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, address.getAddress());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setDate(3, address.getCreateDate());
            preparedStatement.setDate(4, address.getModifyDate());

            return preparedStatement;

        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().intValue();
    }

    public void update(Address address, int addressId, Connection conn) throws SQLException {
        String sql = "UPDATE address SET address = ? ,city = ? ,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                address.getAddress(),
                address.getCity(),
                new Date(System.currentTimeMillis()),
                addressId
        );
    }

    public List<Address> getAllAddress() {
        String sql = "SELECT * FROM address WHERE delete_date IS NULL ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class));
    }

    public List<Address> getAllDeletedAddress() {
        String sql = "SELECT * FROM address WHERE delete_date IS NOT NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class));
    }

    public Address findByAddressId(int addressId) {
        String sql = "SELECT * FROM address WHERE delete_date IS NULL &&  id = ? ";
        return jdbcTemplate.query(sql, new Object[]{addressId}, new BeanPropertyRowMapper<>(Address.class))
                .stream().findAny().orElse(null);
    }
}
