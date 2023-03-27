//package com.mkyong.methods;
//
//import com.mkyong.orders.service.OrdersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.sql.SQLException;
//
//@Component
//public class ShowAllNeedInfo {
//    private ApplicationContext context;
//    @Autowired
//    private OrdersService ordersService;
//
//    public ShowAllNeedInfo(ApplicationContext context) {
//        this.context = context;
//    }
//
//    public void showSingleOrderInfo(int orderId) throws SQLException {
//        ordersService.getSingleOrderInfo(orderId);
//    }
//
//    public void showAllOrderInfo() throws SQLException {
//        ordersService.getAllOrderInfo();
//    }
//
//    public void showAllOrdersInfoByDate(Date date) throws SQLException {
//        ordersService.getOrdersInfoByDate(date);
//    }
//}
