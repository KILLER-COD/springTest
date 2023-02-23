package com.mkyong.product.dao;

import com.mkyong.product.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    public void  insert(Product product);
    public Product findByProductId(int product_id);

    void update(Product product, int productId) throws SQLException;
    void update(String productName,String productType,int productId) throws SQLException;
    void deleteHard(int productId) throws SQLException;
    void deleteSoft(int productId) throws SQLException;
    ArrayList<Product> getAllProduct() throws SQLException;
    ArrayList<Product> getAllDeletedProduct() throws SQLException;
}
