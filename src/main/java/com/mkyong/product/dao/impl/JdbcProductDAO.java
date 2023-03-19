package com.mkyong.product.dao.impl;

import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class JdbcProductDAO implements ProductDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Product product, Connection conn) {
        String sql = "INSERT INTO product (product_name,product_type,create_date,modify_date) VALUES ( ?,?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connecting -> {
            PreparedStatement preparedStatement = connecting.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductType());
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));

            return preparedStatement;
        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().intValue();
    }


    public Product findByProductId(int productId) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, new BeanPropertyRowMapper<>(Product.class))
                .stream().findAny().orElse(null);
    }

    public void deleteSoft(int productId) {
        String sql = "UPDATE product SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                productId
        );
    }

    public void deleteHard(int productId) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql);

    }


    public void update(Product product, int productId) throws SQLException {
        String sql = "UPDATE product SET product_name = ? ,product_type = ? ,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                product.getProductName(),
                product.getProductType(),
                new Date(System.currentTimeMillis()),
                productId
        );
    }

    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM product WHERE ISNULL(delete_date)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> getAllDeletedProduct() {
        String sql = "SELECT * FROM product WHERE !ISNULL(delete_date)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));

    }
}