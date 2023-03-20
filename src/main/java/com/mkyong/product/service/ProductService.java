package com.mkyong.product.service;

import com.mkyong.goods.model.ShowGoods;
import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

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
        product.setModifyDate(new Date(System.currentTimeMillis()));
        return productDAO.insert(product, conn);

    }

    public void changeProduct(Product product, int productId) throws SQLException {
        productDAO.update(product, productId);
    }

    public void checkedUpdateProduct(ShowGoods showGoods, int productId) throws SQLException {
        Product product = findByProductId(productId);
        if (!product.getProductName().equals(showGoods.getProduct().getProductName())) {
            product.setProductName(showGoods.getProduct().getProductName());
        }
        if (!product.getProductType().equals(showGoods.getProduct().getProductType())) {
            product.setProductType(showGoods.getProduct().getProductType());
        }

        changeProduct(product, productId);
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

//    public void deleteProduct(int productId) throws SQLException {
//        System.out.println("Set Delete Product | 1 - hard | 2 - Soft");
//        int deleteType = consoleInputService.readInt();
//        if (deleteType == 1) {
//            productDAO.deleteHard(productId);
//        } else if (deleteType == 2) {
//            productDAO.deleteSoft(productId);
//        } else {
//            System.out.println("Error : Set correct Number ");
//            deleteProduct(productId);
//        }
//    }

//    public void changeProduct() throws SQLException {
//        productsPrint();
//        System.out.println("----------------  Set Product  ID ");
//        int productId = consoleInputService.readInt();
//        while (!existsById(productId)) {
//            System.out.println("Incorrect id----------------  Set Product  ID ");
//            productId = consoleInputService.readInt();
//        }
//        Product product = productDAO.findByProductId(productId);
//        System.out.println(product.toString());
//
//        System.out.println("Set Product Name");
//        String productName = consoleInputService.readString();
//        while (productName.equals("")) {
//            System.out.println("Set Product Name");
//            productName = consoleInputService.readString();
//        }
//        System.out.println("Set Product Type ");
//        String productType = consoleInputService.readString();
//        while (productType.equals("")) {
//            System.out.println("Set Product Name");
//            productType = consoleInputService.readString();
//        }
//
//        product.setProductName(productName);
//        product.setProductType(productType);
//        productDAO.update(product, productId);
//    }


}
