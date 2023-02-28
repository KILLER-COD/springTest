package com.mkyong.product.service;

import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

@Component
public class ProductService {

    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    @Autowired
    private ProductDAO productDAO;

    public ArrayList<Product> getAllProduct() throws SQLException {
        return productDAO.getAllProduct();
    }

    public ArrayList<Product> getAllDeletedProduct() throws SQLException{
        return productDAO.getAllDeletedProduct();
    }

    public void deleteProduct(int productId) throws SQLException {
        System.out.println("Set Delete Product | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            productDAO.deleteHard(productId);
        } else if (deleteType == 2) {
            productDAO.deleteSoft(productId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteProduct(productId);
        }
    }

    public int addNewProduct( Connection conn) {
        String productName;
        String productType;

            System.out.println("Set Product Name");
            productName  = scanner1.next();
            System.out.println("Set Product Type ");
            productType  = scanner1.nextLine();

            Product product = new Product();
            product.setProductName(productName);
            product.setProductType(productType);
            product.setCreateDate(new Date(System.currentTimeMillis()));
            product.setModifyDate(new Date(System.currentTimeMillis()));
            return productDAO.insert(product,conn);

    }

    public void changeProduct() throws SQLException {
        productsPrint();
        System.out.println("----------------  Set Product  ID ");
        int productId = scanner2.nextInt();

        Product product = productDAO.findByProductId(productId);
        System.out.println(product.toString());

        String productName;
        String productType;

        System.out.println("Set Product Name");
        productName  = scanner1.nextLine();
        while (productName.equals("")){
            System.out.println("Set Product Name");
            productName  = scanner1.nextLine();
        }
        System.out.println("Set Product Type ");
        productType  = scanner1.nextLine();
        while (productType.equals("")){
            System.out.println("Set Product Name");
            productType  = scanner1.nextLine();
        }

        product.setProductName(productName);
        product.setProductType(productType);
        productDAO.update(product,productId);
    }

    public void productsPrint(){
        try {
            ArrayList<Product> productList =  productDAO.getAllProduct();
            for (Product product : productList ) {
                System.out.println(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int productId){
        Product retInfo = productDAO.findByProductId(productId);
        if (retInfo == null){
            return false;
        }
        return true;
    }

}
