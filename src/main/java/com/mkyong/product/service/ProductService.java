package com.mkyong.product.service;

import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

public class ProductService {

    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);

    public ArrayList<Product> getAllProduct(ProductDAO productDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return productDAO.getAllProduct();
    }

    public ArrayList<Product> getAllDeletedProduct(ProductDAO productDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return productDAO.getAllDeletedProduct();
    }

    public void deleteProduct(ProductDAO productDAO,int productId) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Delete Product | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            productDAO.deleteHard(productId);
        } else if (deleteType == 2) {
            productDAO.deleteSoft(productId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteProduct(productDAO,productId);
        }
    }

    public void addNewProduct(ProductDAO productDAO) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Set count add Product ");
        int addCount = scanner.nextInt();
        String productName;
        String productType;

        for (int i = 0; i < addCount; i++) {
            System.out.println("Count add - " + i + "\n Set Product Name");
            productName  = scanner1.nextLine();
            System.out.println("Set Product Type ");
            productType  = scanner1.nextLine();

            Product product = new Product();
            product.setProductName(productName);
            product.setProductType(productType);
            product.setCreateDate(new Date(System.currentTimeMillis()));
            product.setModifyDate(new Date(System.currentTimeMillis()));

            productDAO.insert(product);
        }

    }

    public void changeProduct(ProductDAO productDAO) throws SQLException, InterruptedException {

        ArrayList<Product> productList =  productDAO.getAllProduct();
        for (Product product : productList ) {
            System.out.println(product);
        }

        Thread.sleep(500);
        System.out.println("---------------- \n Set Product  ID ");
        int shopInfoId = scanner2.nextInt();

        Product product = productDAO.findByProductId(shopInfoId);
        System.out.println(product.toString());

        System.out.println("---------------- \n What do you change  \n 1- Change Product name -- 2- Change Product Type -- 3- Change all  ");

        int change_num = scanner2.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        String productName = null;
        String productType = null;

        switch (change_num) {

            case 1:
                System.out.println("---------------- \n Set new Product Name");
                productName = scanner1.nextLine();
                productDAO.update(productName, productType, shopInfoId);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new product Type");
                productType = scanner1.nextLine();
                productDAO.update(productName,productType, shopInfoId);
                scanner1.close();
                break;

            default:
                System.out.println("---------------- \n Change All \n Set new Product Name ");
                productName = scanner1.nextLine();
                System.out.println("Set new product Type ");
                productType = scanner1.nextLine();
                productDAO.update(productName, productType, shopInfoId);
                scanner1.close();

        }
        scanner2.close();
    }
}
