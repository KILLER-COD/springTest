package com.mkyong.orders.service;

import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

@Component
public class OrdersService {

    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private ShopsService shopsService;

    public ArrayList<Orders> getAllOrders() throws SQLException {
        return ordersDAO.getAllOrders();
    }

    public ArrayList<Orders> getAllDeletedOrders() throws SQLException {
        return ordersDAO.getAllDeletedOrders();
    }

    public void deleteOrders(OrdersDAO ordersDAO,int ordersId) throws SQLException {
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

    public int addNewOrders( Connection conn)  {
        int shopId;
        shopsService.shopsPrint();
        System.out.println("Set Shop id");
        shopId  = scanner1.nextInt();


        Orders orders = new Orders();
            orders.setShopId(shopId);
            orders.setCreateDate(new Date(System.currentTimeMillis()));
            orders.setModifyDate(new Date(System.currentTimeMillis()));

         return  ordersDAO.insert(orders,conn);


    }

    public void changeOrders() throws SQLException{

        ordersPrint();

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

    public void ordersPrint(){
        try {
            ArrayList<Orders> ordersList = ordersDAO.getAllOrders();
            for (Orders orders : ordersList) {
                System.out.println(orders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
