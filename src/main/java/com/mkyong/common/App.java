package com.mkyong.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class App {

    private DataSource dataSource;

    public static void main(String[] args) throws SQLException, InterruptedException {
        new App().start();
    }

    public void start() throws SQLException, InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.mkyong");
        this.dataSource = (DataSource) context.getBean("dataSource");
//        AddNewTransactionMethod addNewTransactionMethod = context.getBean(AddNewTransactionMethod.class);
//        ChangeNewTransactionMethod changeNewTransactionMethod = context.getBean(ChangeNewTransactionMethod.class);
//        ShowAllNeedInfo showAllNeedInfo = context.getBean(ShowAllNeedInfo.class);
//        OrdersService ordersService1 = context.getBean(OrdersService.class);
//        ConsoleInputService consoleInputService = context.getBean(ConsoleInputService.class);

//      //      Show single Order info
//        ordersService1.ordersPrint();
//        System.out.println("Choose Order id");
//        int orderId = consoleInputService.readInt();
//        showAllNeedInfo.showSingleOrderInfo(orderId);
//
//      //      Show All Orders info
//      showAllNeedInfo.showAllOrderInfo();
//
//      //      Show Orders By Set Date
//      System.out.println("Set orders Date ");
//      String setDateStr = consoleInputService.readString();
//      Date date= Date.valueOf(setDateStr);
//      showAllNeedInfo.showAllOrdersInfoByDate(date);


//                       Change Methods
//        Change Goods
//      changeNewTransactionMethod.changeGoodsMethod();

//        Change Product
//        changeNewTransactionMethod.changeProductMethod();

//        Change Order
//      changeNewTransactionMethod.changeOrdersMethod();

//        Change Address
//      changeNewTransactionMethod.changeAddressMethod();

//        Change Shop
//      changeNewTransactionMethod.changeShopsMethod();

//        Change Shop Info
//      changeNewTransactionMethod.changeShopsInfoMethod();

//        Change Order Goods
//      changeNewTransactionMethod.changeOrdersGoodsMethod();


//                Add methods
//        Add new Product in DataBase
//      addNewTransactionMethod.addNewProductMethod();

//        Add new Goods in DataBase
//      addNewTransactionMethod.addNewGoodsMethod();

//        Add new Order in DataBase
//      addNewTransactionMethod.addNewOrdersMethod();

//        Add new Shop in DataBase
//      addNewTransactionMethod.addNewShopMethod();

//        Add new Address in DataBase
//      addNewTransactionMethod.addNewAddressMethod();


    }

}

