package com.mkyong.common;

import com.mkyong.methods.AddNewTransactionMethod;
import com.mkyong.methods.ChangeNewTransactionMethod;
import com.mkyong.methods.ShowAllNeedInfo;
import com.mkyong.orders.service.OrdersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    private DataSource dataSource;


    public static void main(String[] args) throws SQLException, InterruptedException {
        new App().start();
    }

    public void start() throws SQLException, InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.mkyong");
        this.dataSource = (DataSource) context.getBean("dataSource");
        AddNewTransactionMethod addNewTransactionMethod = context.getBean(AddNewTransactionMethod.class);
        ChangeNewTransactionMethod changeNewTransactionMethod = context.getBean(ChangeNewTransactionMethod.class);
        ShowAllNeedInfo showAllNeedInfo = context.getBean(ShowAllNeedInfo.class);
        OrdersService ordersService1 = context.getBean(OrdersService.class);
        ordersService1.ordersPrint();
        Scanner scanner = new Scanner(System.in);

//      //      Show single Order info
//      System.out.println("Choose Order id");
//      int orderId = scanner.nextInt();
//      showAllNeedInfo.showSingleOrderInfo(orderId);
//
//      //      Show All Orders info
//      showAllNeedInfo.showAllOrderInfo();
//
//      //      Show Orders By Set Date
//      System.out.println("Set orders Date ");
//      String setDateStr = scanner.nextLine();
//      Date date= Date.valueOf(setDateStr);
//      showAllNeedInfo.showAllOrdersInfoByDate(date);


//                       Change Methods
//        Change Goods
//      changeNewTransactionMethod.changeGoodsMethod();

//        Change Product
//      changeNewTransactionMethod.changeProductMethod();

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

