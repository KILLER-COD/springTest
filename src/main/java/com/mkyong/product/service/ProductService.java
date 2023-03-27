package com.mkyong.product.service;

import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public List<Product> getAllProduct() throws SQLException {
        return productDAO.getAllProduct();
    }

    public List<Product> getAllDeletedProduct() throws SQLException {
        return productDAO.getAllDeletedProduct();
    }

    public Product findByProductId(int id) throws SQLException {
        return productDAO.findByProductId(id);
    }

    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteSoft(productId);
    }


    public int addNewProduct(Product product, Connection conn) {
        product.setCreateDate(new Date(System.currentTimeMillis()));
        product.setModifyDate(product.getCreateDate());
        return productDAO.insert(product, conn);

    }

    public void changeProduct(Product product, int productId) throws SQLException {
        productDAO.update(product, productId);
    }

    public void productsPrint() {
        try {
            productDAO.getAllProduct().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int productId) {
        Product retInfo = productDAO.findByProductId(productId);
        return retInfo != null;
    }
}
