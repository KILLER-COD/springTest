package com.mkyong.goods.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;


import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

public class GoodsService {
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);

    public ArrayList<Goods> getAllGoods(GoodsDAO goodsDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return goodsDAO.getAllGoods();
    }

    public ArrayList<Goods> getAllDeletedGoods(GoodsDAO goodsDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return goodsDAO.getAllDeletedGoods();
    }

    public void deleteGoods(GoodsDAO goodsDAO,int goodsId) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Delete Product | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            goodsDAO.deleteHard(goodsId);
        } else if (deleteType == 2) {
            goodsDAO.deleteSoft(goodsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteGoods(goodsDAO,goodsId);
        }
    }

    public void addNewGoods(GoodsDAO goodsDAO) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Set count add Goods ");
        int addCount = scanner.nextInt();
        String goodsName;
        String goodsType;
        double goodsPrice;
        int productId;

        for (int i = 0; i < addCount; i++) {
            System.out.println("Count add - " + i + "\n Set Goods Name");
            goodsName  = scanner1.nextLine();
            System.out.println("Set Goods Type ");
            goodsType  = scanner1.nextLine();
            System.out.println("Set Goods Price ");
            goodsPrice  = scanner1.nextDouble();
            System.out.println("Set Product Id ");
            productId  = scanner1.nextInt();

            Goods goods = new Goods();
            goods.setGoodsName(goodsName);
            goods.setGoodsType(goodsType);
            goods.setGoodsPrice(goodsPrice);
            goods.setProductId(productId);
            goods.setCreateDate(new Date(System.currentTimeMillis()));
            goods.setModifyDate(new Date(System.currentTimeMillis()));

            goodsDAO.insert(goods);
        }

    }

    public void changeGoods(GoodsDAO goodsDAO) throws SQLException, InterruptedException {

        ArrayList<Goods> goodsList =  goodsDAO.getAllGoods();
        for (Goods goods : goodsList ) {
            System.out.println(goods);
        }

        Thread.sleep(500);
        System.out.println("---------------- \n Set Goods  ID ");
        int goodsId = scanner2.nextInt();

        Goods goods = goodsDAO.findByGoodsId(goodsId);
        System.out.println(goods.toString());

        System.out.println("---------------- \n What do you change  \n 1- Change Goods name -- 2- Change Goods Type -- 3-Change Goods Price  -- 4- Change all  ");

        int change_num = scanner2.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        String goodsName = null;
        String goodsType = null;
        double goodsPrice = -1;
        int productId = -1;

        switch (change_num) {

            case 1:
                System.out.println("---------------- \n Set new Goods Name");
                goodsName = scanner1.nextLine();
                goodsDAO.update(goodsName, goodsType,goodsPrice,productId, goodsId);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new Goods Type");
                goodsType = scanner1.nextLine();
                goodsDAO.update(goodsName,goodsType,goodsPrice,productId, goodsId);
                scanner1.close();
                break;

            case 3:
                System.out.println("---------------- \n Set new Goods Price");
                goodsPrice = scanner1.nextDouble();
                goodsDAO.update(goodsName,goodsType, goodsPrice,productId,goodsId);
                scanner1.close();
                break;

            case 4:
                System.out.println("---------------- \n Set new Product ID");
                productId = scanner1.nextInt();
                goodsDAO.update(goodsName,goodsType, goodsPrice,productId,goodsId);
                scanner1.close();
                break;

            default:
                System.out.println("---------------- \n Change All \n Set new Goods Name ");
                goodsName = scanner1.nextLine();
                System.out.println("Set new Goods Type ");
                goodsType = scanner1.nextLine();
                System.out.println("Set new Goods Price ");
                goodsPrice = scanner1.nextDouble();
                System.out.println("Set new Product ID ");
                productId = scanner1.nextInt();
                goodsDAO.update(goodsName, goodsType,goodsPrice,productId, goodsId);
                scanner1.close();

        }
        scanner2.close();
    }
}
