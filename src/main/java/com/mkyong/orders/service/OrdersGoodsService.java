package com.mkyong.orders.service;

import com.mkyong.goods.service.GoodsService;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.orders.model.OrdersGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class OrdersGoodsService {
    @Autowired
    private OrdersGoodsDAO ordersGoodsDAO;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ConsoleInputService consoleInputService;

    public ArrayList<OrdersGoods> getAllOrdersGoods() throws SQLException {
        return ordersGoodsDAO.getAllOrdersGoods();
    }

    public ArrayList<OrdersGoods> getAllDeletedOrdersGoods() throws SQLException {
        return ordersGoodsDAO.getAllDeletedOrdersGoods();
    }

    public void deleteOrdersGoods(int ordersGoodsId) throws SQLException {
        System.out.println("Set Delete Orders Goods | 1 - hard | 2 - Soft");
        int deleteType = consoleInputService.readInt();
        if (deleteType == 1) {
            ordersGoodsDAO.deleteHard(ordersGoodsId);
        } else if (deleteType == 2) {
            ordersGoodsDAO.deleteSoft(ordersGoodsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteOrdersGoods(ordersGoodsId);
        }
    }

    public ArrayList<Integer> addNewOrdersGoods(int ordersId, Connection conn) throws InterruptedException {
        OrdersGoods ordersGoods;

        goodsService.goodsPrint();
        ArrayList<Integer> ordersGoodsList = new ArrayList<>();
        while (true) {
            System.out.println("Set Goods Id ");
            int goodsId = consoleInputService.readInt();
            System.out.println("Set Goods count ");
            double goodsCount = consoleInputService.readDouble();

            ordersGoods = new OrdersGoods();
            ordersGoods.setOrdersId(ordersId);
            ordersGoods.setGoodsId(goodsId);
            ordersGoods.setGoodsCount(goodsCount);
            ordersGoods.setCreateDate(new Date(System.currentTimeMillis()));
            ordersGoods.setModifyDate(new Date(System.currentTimeMillis()));

            ordersGoodsList.add(ordersGoodsDAO.insert(ordersGoods, conn));
            System.out.println("Add new Goods in Order | 1 - yes | 2 - No");
            int addNewGoods = consoleInputService.readInt();
            if (addNewGoods == 2) {
                break;
            }
        }
        return ordersGoodsList;

    }

    public void changeOrdersGoods() throws SQLException {
        System.out.println("---------------- \n What do you change  \n 1- Change Orders Goods orders_Id -- 2- Change Goods_ID -- 3-Change Goods_Count  -- 4- Change all  ");

        ordersGoodsPrint();
        System.out.println("---------------- \n Set Orders Goods  ID ");
        int ordersGoodsId = consoleInputService.readInt();
        while (!existsById(ordersGoodsId)) {
            System.out.println("---------------- \n Set Correct Orders Goods ID ");
            ordersGoodsId = consoleInputService.readInt();
        }
        OrdersGoods ordersGoods = ordersGoodsDAO.findByOrdersGoodsId(ordersGoodsId);
        System.out.println(ordersGoods.toString());

        ordersService.ordersPrint();
        System.out.println("----------------  Set new Orders Goods orders_Id " + ordersGoods.getOrdersId());
        int ordersId = consoleInputService.readInt();
        while (!goodsService.existsById(ordersId)) {
            System.out.println("Set new Orders Goods  Orders_ID = " + ordersGoods.getOrdersId());
            ordersId = consoleInputService.readInt();
        }
        goodsService.goodsPrint();
        System.out.println("Set new Orders Goods  Goods_ID " + ordersGoods.getGoodsId());
        int goodsId = consoleInputService.readInt();
        while (!goodsService.existsById(goodsId)) {
            System.out.println("Set new Orders Goods  Goods_ID = " + ordersGoods.getGoodsId());
            goodsId = consoleInputService.readInt();
        }

        System.out.println("Set new  Orders Goods Goods_Count = " + ordersGoods.getGoodsCount());
        double goodsCount = consoleInputService.readDouble();
        while (goodsCount == 0.0) {
            System.out.println("Set Correct Count  ----------------   Change Goods Count = " + ordersGoods.getGoodsCount());
            goodsCount = consoleInputService.readDouble();
        }

        ordersGoods.setOrdersId(ordersId);
        ordersGoods.setGoodsId(goodsId);
        ordersGoods.setGoodsCount(goodsCount);


        ordersGoodsDAO.update(ordersGoods, ordersGoodsId);
    }

    public void ordersGoodsPrint() {
        try {
            ordersGoodsDAO.getAllOrdersGoods().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int ordersGoodsId) {
        OrdersGoods retInfo = ordersGoodsDAO.findByOrdersGoodsId(ordersGoodsId);
        return retInfo != null;
    }
}
