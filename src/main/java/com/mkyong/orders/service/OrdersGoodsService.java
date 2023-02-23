package com.mkyong.orders.service;

import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.orders.model.OrdersGoods;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrdersGoodsService {
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);

    public ArrayList<OrdersGoods> getAllOrdersGoods(OrdersGoodsDAO ordersGoodsDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return ordersGoodsDAO.getAllOrdersGoods();
    }

    public ArrayList<OrdersGoods> getAllDeletedOrdersGoods(OrdersGoodsDAO ordersGoodsDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return ordersGoodsDAO.getAllDeletedOrdersGoods();
    }

    public void deleteOrdersGoods(OrdersGoodsDAO ordersGoodsDAO,int ordersGoodsId) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Delete Orders Goods | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            ordersGoodsDAO.deleteHard(ordersGoodsId);
        } else if (deleteType == 2) {
            ordersGoodsDAO.deleteSoft(ordersGoodsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteOrdersGoods(ordersGoodsDAO,ordersGoodsId);
        }
    }

    public void addNewOrdersGoods(OrdersGoodsDAO ordersGoodsDAO) throws InterruptedException {
        Thread.sleep(500);
        int ordersId;
        int goodsId;
        double goodsCount;

            System.out.println("Set Orders Id");
            ordersId  = scanner1.nextInt();
            System.out.println("Set Goods Id ");
            goodsId = scanner1.nextInt();
            System.out.println("Set  Goods count ");
            goodsCount = scanner1.nextDouble();

            OrdersGoods ordersGoods = new OrdersGoods();
            ordersGoods.setOrdersId(ordersId);
            ordersGoods.setGoodsId(goodsId);
            ordersGoods.setGoodsCount(goodsCount);
            ordersGoods.setCreateDate(new Date(System.currentTimeMillis()));
            ordersGoods.setModifyDate(new Date(System.currentTimeMillis()));

            ordersGoodsDAO.insert(ordersGoods);

    }

    public void changeOrdersGoods(OrdersGoodsDAO ordersGoodsDAO) throws SQLException, InterruptedException {

        ArrayList<OrdersGoods> ordersGoodsList =  ordersGoodsDAO.getAllOrdersGoods();
        for (OrdersGoods ordersGoods : ordersGoodsList ) {
            System.out.println(ordersGoods);
        }

        Thread.sleep(500);
        System.out.println("---------------- \n Set Orders Goods  ID ");
        int ordersGoodsId = scanner2.nextInt();

        OrdersGoods ordersGoods = ordersGoodsDAO.findByOrdersGoodsId(ordersGoodsId);
        System.out.println(ordersGoods.toString());

        System.out.println("---------------- \n What do you change  \n 1- Change Orders Goods orders_Id -- 2- Change Goods_ID -- 3-Change Goods_Count  -- 4- Change all  ");
        int change_num = scanner2.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        int ordersId = -1;
        int goodsId = -1;
        double goodsCount = -1;

        switch (change_num) {

            case 1:
                System.out.println("---------------- \n Set new Orders Goods orders_Id ");
                ordersId = scanner1.nextInt();
                ordersGoodsDAO.update(ordersId, goodsId,goodsCount, ordersGoodsId);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new Orders Goods  Goods_ID");
                goodsId = scanner1.nextInt();
                ordersGoodsDAO.update(ordersId,goodsId,goodsCount, ordersGoodsId);
                scanner1.close();
                break;

            case 3:
                System.out.println("---------------- \n Set new  Orders Goods Goods_Count");
                goodsCount = scanner1.nextDouble();
                ordersGoodsDAO.update(ordersId,goodsId, goodsCount,ordersGoodsId);
                scanner1.close();
                break;


            default:
                System.out.println("---------------- \n Change All \n Set new Orders Goods orders_Id ");
                ordersId = scanner1.nextInt();
                System.out.println("Set new Orders Goods  Goods_ID ");
                goodsId = scanner1.nextInt();
                System.out.println("Set new  Orders Goods Goods_Count ");
                goodsCount = scanner1.nextDouble();
                ordersGoodsDAO.update(ordersId, goodsId,goodsCount, ordersGoodsId);
                scanner1.close();

        }

        scanner2.close();
    }
}
