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

    @Autowired
    private OrdersGoodsDAO ordersGoodsDAO;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrdersService ordersService;

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
            goodsId = scanner.nextInt();
            System.out.println("Set  Goods count ");
            goodsCount = scanner.nextDouble();

            ordersGoods = new OrdersGoods();
            ordersGoods.setOrdersId(ordersId);
            ordersGoods.setGoodsId(goodsId);
            ordersGoods.setGoodsCount(goodsCount);
            ordersGoods.setCreateDate(new Date(System.currentTimeMillis()));
            ordersGoods.setModifyDate(new Date(System.currentTimeMillis()));

            ordersGoodsList.add(ordersGoodsDAO.insert(ordersGoods,conn));
            System.out.println("Add new Goods in Order | 1 - yes | 2 - No");
            int addNewGoods = scanner.nextInt();
            if (addNewGoods == 2 ) {
                break;
            }
        }
            return ordersGoodsList;

    }

    public void changeOrdersGoods() throws SQLException {
        System.out.println("---------------- \n What do you change  \n 1- Change Orders Goods orders_Id -- 2- Change Goods_ID -- 3-Change Goods_Count  -- 4- Change all  ");

        Scanner scanner = new Scanner(System.in);

        ordersGoodsPrint();
        System.out.println("---------------- \n Set Orders Goods  ID ");
        int ordersGoodsId = scanner.nextInt();
        while (!existsById(ordersGoodsId) && ordersGoodsId > 0){
            System.out.println("---------------- \n Set Correct Orders Goods ID ");
            ordersGoodsId = scanner.nextInt();
        }
        OrdersGoods ordersGoods = ordersGoodsDAO.findByOrdersGoodsId(ordersGoodsId);
        System.out.println(ordersGoods.toString());

        ordersService.getAllOrders().forEach(System.out::println);
        System.out.println("----------------  Set new Orders Goods orders_Id " + ordersGoods.getOrdersId());
        int ordersId = scanner.nextInt();
        while (!goodsService.existsById(ordersId) && ordersId < 0){
            System.out.println("Set new Orders Goods  Orders_ID = " + ordersGoods.getOrdersId());
            ordersId = scanner.nextInt();
        }
        goodsService.getAllGoods().forEach(System.out::println);
        System.out.println("Set new Orders Goods  Goods_ID " + ordersGoods.getGoodsId());
        int goodsId = scanner.nextInt();
        while (!goodsService.existsById(goodsId) && goodsId < 0){
            System.out.println("Set new Orders Goods  Goods_ID = " + ordersGoods.getGoodsId());
            goodsId = scanner.nextInt();
        }

        System.out.println("Set new  Orders Goods Goods_Count = " + ordersGoods.getGoodsCount());
        double goodsCount = scanner.nextDouble();
        while (goodsCount == 0.0 || goodsCount < 0){
            System.out.println("Set Correct Count  ----------------   Change Goods Count = " + ordersGoods.getGoodsCount());
            goodsCount = scanner.nextDouble();
        }

        ordersGoods.setOrdersId(ordersId);
        ordersGoods.setGoodsId(goodsId);
        ordersGoods.setGoodsCount(goodsCount);


        ordersGoodsDAO.update(ordersGoods,ordersGoodsId);
        scanner.close();
    }

    public void ordersGoodsPrint(){
        try {
            ArrayList<OrdersGoods> ordersGoodsList = ordersGoodsDAO.getAllOrdersGoods();
            ordersGoodsList.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int ordersGoodsId){
        OrdersGoods retInfo = ordersGoodsDAO.findByOrdersGoodsId(ordersGoodsId);
        if (retInfo == null){
            return false;
        }
        return true;
    }
}
