package com.mkyong.goods.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class GoodsService {
    @Autowired
    private ProductService productService;
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private ConsoleInputService consoleInputService;

    public ArrayList<Goods> getAllGoods() throws SQLException {
        return goodsDAO.getAllGoods();
    }

    public ArrayList<Goods> getAllDeletedGoods() throws SQLException {
        return goodsDAO.getAllDeletedGoods();
    }

    public void deleteGoods(int goodsId) throws SQLException {
        System.out.println("Set Delete Product | 1 - hard | 2 - Soft");

        int deleteType = consoleInputService.readInt();
        if (deleteType == 1) {
            goodsDAO.deleteHard(goodsId);
        } else if (deleteType == 2) {
            goodsDAO.deleteSoft(goodsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteGoods(goodsId);
        }
    }

    public int addNewGoods(Connection conn) throws InterruptedException {
        int choseProductAddType;
        String goodsName;
        String goodsType;
        double goodsPrice;
        int productId = -1;

        System.out.println("Set Goods Name");
        goodsName = consoleInputService.readString();
        System.out.println("Set Goods Type ");
        goodsType = consoleInputService.readString();
        System.out.println("Set Goods Price ");
        goodsPrice = consoleInputService.readDouble();
        System.out.println("Set Product Id (1) or Create new Product (2)");
        choseProductAddType = consoleInputService.readInt();
        boolean existById = false;
        if (choseProductAddType == 1) {
            while (!existById) {

                productService.productsPrint();
                System.out.println("Set Product Id");
                productId = consoleInputService.readInt();
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
        System.out.println(goods);
        int goodsId = goodsDAO.insert(goods, conn);
        while (goodsId < 0) {
            goodsId = addNewGoods(conn);
        }
        return goodsId;


    }

    public void changeGoods(Connection conn) throws SQLException {
        int productId = -1;

        goodsPrint();
        System.out.println("----------------  Set Goods  ID ");
        int goodsId = consoleInputService.readInt();

        while (!existsById(goodsId)) {
            System.out.println(" ---------------- Incorrect id,   ---------------- Set Goods  ID ");
            goodsId = consoleInputService.readInt();
        }

        Goods goods = goodsDAO.findByGoodsId(goodsId);
        System.out.println(goods.toString());

        System.out.println("----------------   Change Goods name =" + goods.getGoodsName());
        String goodsName = consoleInputService.readString();
        while (goodsName.equals("")) {
            System.out.println("\"" + goodsName + "\" Set Correct Name ");
            System.out.println("--------------- Change Goods name =" + goods.getGoodsName());
            goodsName = consoleInputService.readString();
        }

        System.out.println("---------------- Change Goods Type = " + goods.getGoodsType());
        String goodsType = consoleInputService.readString();
        while (goodsType.equals("")) {
            System.out.println("\"" + goodsType + "\" ----------------  Set Goods Type =" + goods.getGoodsType());
            goodsType = consoleInputService.readString();
        }

        System.out.println("---------------- Set new Goods Price = " + goods.getGoodsPrice());
        double goodsPrice = consoleInputService.readDouble();
        while (goodsPrice == 0.0) {
            System.out.println("Set Correct Price  ----------------   Change Goods Price = " + goods.getGoodsPrice());
            goodsPrice = consoleInputService.readDouble();
        }

        System.out.println("Change Product Id (1) or Create new Product  and Add(2)");
        int choseProductAddType = consoleInputService.readInt();
        boolean existById = false;
        if (choseProductAddType == 1) {
            while (!existById) {
                productService.productsPrint();
                System.out.println("Set Product Id");
                productId = consoleInputService.readInt();
                existById = productService.existsById(productId);
            }
        } else if (choseProductAddType == 2) {
            productId = productService.addNewProduct(conn);
            while (productId < 0) {
                productId = productService.addNewProduct(conn);
            }
        }

        System.out.println("Name = " + goodsName + "\n Type = " + goodsType + "\n Price = " + goodsPrice + "\n Product Id  = " + productId);

        goods.setGoodsName(goodsName);
        goods.setGoodsType(goodsType);
        goods.setGoodsPrice(goodsPrice);
        goods.setProductId(productId);

        goodsDAO.update(goods, goodsId, conn);

    }

    public void goodsPrint() {
        try {
            getAllGoods().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int goodsId) {
        Goods retInfo = goodsDAO.findByGoodsId(goodsId);
        return retInfo != null;
    }
}
