package com.mkyong.goods.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import com.mkyong.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class GoodsService {
    @Autowired
    private ProductService productService;
    @Autowired
    private GoodsDAO goodsDAO;

    public ArrayList<Goods> getAllGoods() throws SQLException{
        return goodsDAO.getAllGoods();
    }

    public ArrayList<Goods> getAllDeletedGoods() throws SQLException {
        return goodsDAO.getAllDeletedGoods();
    }

    public void deleteGoods( int goodsId) throws SQLException {
        System.out.println("Set Delete Product | 1 - hard | 2 - Soft");
        Scanner scanner = new Scanner(System.in);

        int deleteType = scanner.nextInt();
        if (deleteType == 1) {
            goodsDAO.deleteHard(goodsId);
        } else if (deleteType == 2) {
            goodsDAO.deleteSoft(goodsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteGoods(goodsId);
        }
    }

    public int addNewGoods( Connection conn) throws InterruptedException {
        int choseProductAddType;
        String goodsName;
        String goodsType;
        double goodsPrice;
        int productId = -1;
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Set Goods Name");
        goodsName = scanner1.nextLine();
        System.out.println("Set Goods Type ");
        goodsType = scanner1.nextLine();
        System.out.println("Set Goods Price ");
        goodsPrice = scanner1.nextDouble();
        System.out.println("Set Product Id (1) or Create new Product (2)");
        choseProductAddType = scanner1.nextInt();
        boolean existById = false;
        if (choseProductAddType == 1) {
            while (!existById) {

                productService.productsPrint();
                System.out.println("Set Product Id");
                productId = scanner1.nextInt();
                existById = productService.existsById(productId);

            }
        } else if (choseProductAddType == 2) {
            productId = productService.addNewProduct(conn);
            while (productId < 0) {
                productId = productService.addNewProduct(conn);
            }
        }

        Goods goods = new Goods();
        goods.setGoodsName(goodsName);
        goods.setGoodsType(goodsType);
        goods.setGoodsPrice(goodsPrice);
        goods.setProductId(productId);
        goods.setCreateDate(new Date(System.currentTimeMillis()));
        goods.setModifyDate(new Date(System.currentTimeMillis()));

        int goodsId = goodsDAO.insert(goods, conn);
        while (goodsId < 0) {
            goodsId = addNewGoods(conn);
        }
        return goodsId;


    }

    public void changeGoods() throws SQLException {
        String goodsName = null;
        String goodsType ;
        double goodsPrice;
        int productId = -1;
        int goodsId;
        Scanner scanner1 = new Scanner(System.in);

        goodsPrint();
        System.out.println("---------------- \n Set Goods  ID ");
        goodsId = scanner1.nextInt();

        while (!existsById(goodsId)){
            System.out.println(" ---------------- \nIncorrect id,  \n ---------------- \n Set Goods  ID ");
            goodsId = scanner1.nextInt();
        }

        Goods goods = goodsDAO.findByGoodsId(goodsId);
        System.out.println(goods.toString());

        System.out.println("----------------   Change Goods name ="+ goods.getGoodsName());
        goodsName = scanner1.next();
        while (goodsName.equals("") ){
            System.out.println("\""+ goodsName +"\" Set Correct Name  \n----------------  \n Change Goods name ="+ goods.getGoodsName());
            goodsName = scanner1.next();
        }

        System.out.println("---------------- \n Change Goods Type = " + goods.getGoodsType());
        goodsType = scanner1.next();
        while (goodsType.equals("") || goodsType.trim().equals("")){
            System.out.println("\""+goodsType+"\" ----------------  \n Set Goods Type =" + goods.getGoodsType());
            goodsType = scanner1.next();
        }

        System.out.println("---------------- \n Set new Goods Price = " + goods.getGoodsPrice());
        goodsPrice = scanner1.nextDouble();
        while (goodsPrice == 0.0 || goodsPrice < 0){
            System.out.println("Set Correct Price \n ----------------  \n Change Goods Price = " + goods.getGoodsPrice());
            goodsPrice = scanner1.nextDouble();
        }

        System.out.println("Change Product Id (1) or Create new Product  and Add(2)");
        int choseProductAddType = scanner1.nextInt();
        boolean existById = false;
        if (choseProductAddType == 1) {
            while (!existById) {
                productService.productsPrint();
                System.out.println("Set Product Id");
                productId = scanner1.nextInt();
                existById = productService.existsById(productId);
            }
        } else if (choseProductAddType == 2) {
            productId = productService.addNewProduct(null);
            while (productId < 0) {
                productId = productService.addNewProduct(null);
            }
        }

        System.out.println("Name = " + goodsName + "\n Type = " + goodsType +"\n Price = " + goodsPrice + "\n Product Id  = " + productId );
        goodsDAO.update(goodsName, goodsType, goodsPrice, productId, goodsId);

        scanner1.close();
    }

    public void goodsPrint(){
        try {
            getAllGoods().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int goodsId){
        Goods retInfo = goodsDAO.findByGoodsId(goodsId);
        if (retInfo == null){
            return false;
        }
        return true;
    }
}
