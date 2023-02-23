package com.mkyong.orders.service;

import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

public class OrdersService {

    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);

    public ArrayList<Orders> getAllOrders(OrdersDAO ordersDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return ordersDAO.getAllOrders();
    }

    public ArrayList<Orders> getAllDeletedOrders(OrdersDAO ordersDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return ordersDAO.getAllDeletedOrders();
    }

    public void deleteOrders(OrdersDAO ordersDAO,int ordersId) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Delete Product | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            ordersDAO.deleteHard(ordersId);
        } else if (deleteType == 2) {
            ordersDAO.deleteSoft(ordersId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteOrders(ordersDAO,ordersId);
        }
    }

    public void addNewOrders(OrdersDAO ordersDAO) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Set count add Goods ");
        int addCount = scanner.nextInt();
        int shopId;


        for (int i = 0; i < addCount; i++) {
            System.out.println("Count add - " + i + "\n Set Shop id");
            shopId  = scanner1.nextInt();

            Orders orders = new Orders();
            orders.setShopId(shopId);
            orders.setCreateDate(new Date(System.currentTimeMillis()));
            orders.setModifyDate(new Date(System.currentTimeMillis()));

            ordersDAO.insert(orders);
        }

    }

    public void changeOrders(OrdersDAO ordersDAO) throws SQLException, InterruptedException {

        ArrayList<Orders> ordersList =  ordersDAO.getAllOrders();
        for (Orders orders : ordersList ) {
            System.out.println(orders);
        }

        Thread.sleep(500);
        System.out.println("---------------- \n Set Orders  ID ");
        int ordersId = scanner2.nextInt();

        Orders orders = ordersDAO.findByOrdersId(ordersId);
        System.out.println(orders.toString());

        System.out.println("---------------- \n Change Shop Id   ");

        Scanner scanner1 = new Scanner(System.in);
        int shopId = scanner1.nextInt();

        ordersDAO.update(shopId, ordersId);
        scanner1.close();
        scanner2.close();
    }
}
