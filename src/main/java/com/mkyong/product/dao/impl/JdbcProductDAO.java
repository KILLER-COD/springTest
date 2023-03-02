package com.mkyong.product.dao.impl;

import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class JdbcProductDAO implements ProductDAO {
    @Autowired
    private DataSource dataSource;

    public int insert(Product product, Connection conn) {

        String sql = "INSERT INTO product (product_name,product_type,create_date,modify_date) VALUES ( ?,?,?,?)";
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductType());
            ps.setDate(3, product.getCreateDate());
            ps.setDate(4, product.getModifyDate());
            ps.executeUpdate();
            int productId = -1;
            ResultSet getGenerateKey = ps.getGeneratedKeys();
            if (getGenerateKey.next()) {
                productId = getGenerateKey.getInt(1);
            }
            ps.close();
            System.out.println(productId);
            return productId;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
//            closeConnection(conn);
        }
    }

    public Product findByProductId(int productId) {

        String sql = "SELECT * FROM product WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            Product product = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("product_type"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
            }
            rs.close();
            ps.close();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void deleteSoft(int productId) {
        String sql = "UPDATE product SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int productId) {
        String sql = "DELETE FROM product WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

    }

//    public void update(String productName, String productType,int productId) throws SQLException {
//
//        Product product =  findByProductId(productId);
//
//        if (productName != null && productType == null){
//            product.setProductName(productName);
//        } else if (productName == null && productType != null) {
//            product.setProductType(productType);
//        } else  {
//           product.setProductName(productName);
//           product.setProductType(productType);
//        }
//        update(product,productId);
//    }

    public void update(Product product, int productId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE product SET product_name = ? ,product_type = ? ,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, product.getProductName());
        ps.setString(2, product.getProductType());
        ps.setDate(3, new Date(System.currentTimeMillis()));
        ps.setInt(4, productId);
        ps.executeUpdate();
        ps.close();

        closeConnection(conn);

    }

    public ArrayList<Product> getAllProduct() {
        String sql = "SELECT * FROM product WHERE ISNULL(delete_date)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Product product = null;
            ArrayList<Product> productList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("Product_name"),
                        rs.getString("Product_type"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                productList.add(product);
            }
            rs.close();
            ps.close();
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<Product> getAllDeletedProduct() {
        String sql = "SELECT * FROM product WHERE !ISNULL(delete_date)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Product product = null;
            ArrayList<Product> productList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("Product_name"),
                        rs.getString("Product_type"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                productList.add(product);
            }
            rs.close();
            ps.close();
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

}