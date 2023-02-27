package com.mkyong.orders.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.goods.service.GoodsService;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.orders.model.OrdersGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
@Component
public class OrdersGoodsService {
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    @Autowired
    private OrdersGoodsDAO ordersGoodsDAO;
    @Autowired
    private GoodsService goodsService;

    public ArrayList<OrdersGoods> getAllOrdersGoods() throws SQLException {
        return ordersGoodsDAO.getAllOrdersGoods();
    }

    public ArrayList<OrdersGoods> getAllDeletedOrdersGoods() throws SQLException {
        return ordersGoodsDAO.getAllDeletedOrdersGoods();
    }

    public void deleteOrdersGoods(int ordersGoodsId) throws SQLException {
        System.out.println("Set Delete Orders Goods | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            ordersGoodsDAO.deleteHard(ordersGoodsId);
        } else if (deleteType == 2) {
            ordersGoodsDAO.deleteSoft(ordersGoodsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteOrdersGoods(ordersGoodsId);
        }
    }

    public ArrayList<Integer> addNewOrdersGoods(int ordersId,Connection conn) throws InterruptedException {
        int goodsId;
        double goodsCount;
        OrdersGoods ordersGoods;

        goodsService.goodsPrint();
        ArrayList<Integer> ordersGoodsList = new ArrayList<>();
        while (true) {
            System.out.println("Set Goods Id ");
            goodsId = scanner1.nextInt();
            System.out.println("Set  Goods count ");
            goodsCount = scanner1.nextDouble();

            ordersGoods = new OrdersGoods();
            ordersGoods.setOrdersId(ordersId);
            ordersGoods.setGoodsId(goodsId);
            ordersGoods.setGoodsCount(goodsCount);
            ordersGoods.setCreateDate(new Date(System.currentTimeMillis()));
            ordersGoods.setModifyDate(new Date(System.currentTimeMillis()));

            ordersGoodsList.add(ordersGoodsDAO.insert(ordersGoods,conn));
            System.out.println("Add new Goods in Order | 1 - yes | 2 - No");
            int addNewGoods = scanner1.nextInt();
            if (addNewGoods == 2 ) {
                break;
            }
        }
            return ordersGoodsList;

    }

    public void changeOrdersGoods() throws SQLException {

        ArrayList<OrdersGoods> ordersGoodsList =  ordersGoodsDAO.getAllOrdersGoods();
        for (OrdersGoods ordersGoods : ordersGoodsList ) {
            System.out.println(ordersGoods);
        }

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
