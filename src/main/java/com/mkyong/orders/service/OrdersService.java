package com.mkyong.orders.service;

import com.mkyong.methods.ConsoleInputService;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.shops.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrdersService {
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private ShopsService shopsService;
    @Autowired
    private ConsoleInputService consoleInputService;

    public List<Orders> getAllOrders() throws SQLException {
        return ordersDAO.getAllOrders();
    }

    public Orders findByOrdersId(int id) throws SQLException {
        return ordersDAO.findByOrdersId(id);
    }

    public void getSingleOrderInfo(int ordersId) throws SQLException {
        if (existsById(ordersId)) {
            ordersDAO.getSingleOrderInfo(ordersId);
        } else {
            System.out.println("Choose correct Order id");
            int newOrdersId = consoleInputService.readInt();
            getSingleOrderInfo(newOrdersId);
        }
    }

    public void getOrdersInfoByDate(Date date) throws SQLException {
        ordersDAO.getAllOrdersInfoByDate(date);
    }

    public void getAllOrderInfo() throws SQLException {
        ordersDAO.getAllOrderInfo();
    }

    public List<Orders> getAllDeletedOrders() throws SQLException {
        return ordersDAO.getAllDeletedOrders();
    }

    public void deleteOrders(int ordersId) throws SQLException {
        System.out.println("Set Delete Orders | 1 - hard | 2 - Soft");
        int deleteType = consoleInputService.readInt();
        if (deleteType == 1) {
            ordersDAO.deleteHard(ordersId);
        } else if (deleteType == 2) {
            ordersDAO.deleteSoft(ordersId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteOrders(ordersId);
        }
    }

    public int addNewOrders(Orders orders, Connection conn) {
        orders.setCreateDate(new Date(System.currentTimeMillis()));
        orders.setModifyDate(new Date(System.currentTimeMillis()));
        return ordersDAO.insert(orders, conn);
    }

    public void changeOrders() throws SQLException {
        ordersPrint();
        System.out.println("---------------- \n Set Order  ID ");
        int ordersId = consoleInputService.readInt();
        while (!existsById(ordersId)) {
            System.out.println("Incorrect Order ID---------------- \n Set Order  ID ");
            ordersId = consoleInputService.readInt();
        }

        Orders orders = ordersDAO.findByOrdersId(ordersId);
        System.out.println(orders.toString());

        shopsService.shopsPrint();
        System.out.println("---------------- \n Change Shop Id   ");
        int shopId = consoleInputService.readInt();
        while (!shopsService.existsById(shopId)) {
            System.out.println("Incorrect Shop Id---------------- \n Change Shop Id   ");
            shopId = consoleInputService.readInt();
        }

        orders.setShopId(shopId);
        ordersDAO.update(orders, ordersId);
    }

    public void ordersPrint() {
        try {
            ordersDAO.getAllOrders().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int ordersId) {
        Orders retInfo = ordersDAO.findByOrdersId(ordersId);
        return retInfo != null;
    }
}
