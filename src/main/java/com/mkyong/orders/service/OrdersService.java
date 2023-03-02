package com.mkyong.orders.service;

import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.shops.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class OrdersService {
    Scanner scanner = new Scanner(System.in);
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private ShopsService shopsService;

    public ArrayList<Orders> getAllOrders() throws SQLException {
        return ordersDAO.getAllOrders();
    }

    public void getSingleOrderInfo(int ordersId) throws SQLException {
        if (existsById(ordersId)) {
            ordersDAO.getSingleOrderInfo(ordersId);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose correct Order id");
            ordersId = scanner.nextInt();
            getSingleOrderInfo(ordersId);
        }
    }

    public void getOrdersInfoByDate(Date date) throws SQLException {
        ordersDAO.getAllOrdersInfoByDate(date);
    }

    public void getAllOrderInfo() throws SQLException {
        ordersDAO.getAllOrderInfo();
    }

    public ArrayList<Orders> getAllDeletedOrders() throws SQLException {
        return ordersDAO.getAllDeletedOrders();
    }

    public void deleteOrders(int ordersId) throws SQLException {
        System.out.println("Set Delete Orders | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1) {
            ordersDAO.deleteHard(ordersId);
        } else if (deleteType == 2) {
            ordersDAO.deleteSoft(ordersId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteOrders(ordersId);
        }
        scanner.close();
    }

    public int addNewOrders(Connection conn) {
        int shopId;
        shopsService.shopsPrint();
        System.out.println("Set Shop id");
        shopId = scanner.nextInt();

        Orders orders = new Orders();
        orders.setShopId(shopId);
        orders.setCreateDate(new Date(System.currentTimeMillis()));
        orders.setModifyDate(new Date(System.currentTimeMillis()));
        scanner.close();
        return ordersDAO.insert(orders, conn);
    }

    public void changeOrders() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ordersPrint();

        System.out.println("---------------- \n Set Orders  ID ");
        int ordersId = scanner.nextInt();
        while (!existsById(ordersId)) {
            System.out.println("---------------- \n Set Orders  ID ");
            ordersId = scanner.nextInt();
        }

        Orders orders = ordersDAO.findByOrdersId(ordersId);
        System.out.println(orders.toString());

        shopsService.shopsPrint();
        System.out.println("---------------- \n Change Shop Id   ");
        int shopId = scanner.nextInt();
        while (!shopsService.existsById(shopId)) {
            System.out.println("Incorrect Shop Id---------------- \n Change Shop Id   ");
            shopId = scanner.nextInt();
        }

        orders.setShopId(shopId);
        ordersDAO.update(orders, ordersId);
        scanner.close();
    }

    public void ordersPrint() {
        try {
            ArrayList<Orders> ordersList = ordersDAO.getAllOrders();
            for (Orders orders : ordersList) {
                System.out.println(orders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int ordersId) {
        Orders retInfo = ordersDAO.findByOrdersId(ordersId);
        if (retInfo == null) {
            return false;
        }
        return true;
    }
}
