package com.mkyong.common;

import com.mkyong.methods.AddNewTransactionMethod;
import com.mkyong.methods.ChangeNewTransactionMethod;
import com.mkyong.methods.ShowAllNeedInfo;
import com.mkyong.orders.service.OrdersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    private DataSource dataSource;


    public static void main( String[] args ) throws SQLException, InterruptedException {
        new App().start();
    }
    public void start() throws SQLException, InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.mkyong");
        this.dataSource = (DataSource) context.getBean("dataSource");
        AddNewTransactionMethod addNewTransactionMethod = context.getBean(AddNewTransactionMethod.class);
        ChangeNewTransactionMethod changeNewTransactionMethod = context.getBean(ChangeNewTransactionMethod.class);
        ShowAllNeedInfo showAllNeedInfo =  context.getBean(ShowAllNeedInfo.class);
        OrdersService ordersService1 = context.getBean(OrdersService.class);
        ordersService1.getAllOrders().forEach(System.out::println);


//      Scanner scanner = new Scanner(System.in);
//      System.out.println("Choose Order id");
//      int orderId = scanner.nextInt();
//      showAllNeedInfo.showSingleOrderInfo(orderId);
//      showAllNeedInfo.showAllOrderInfo();
//
      Scanner scanner = new Scanner(System.in);
      System.out.println("Set orders Date ");
      String setDateStr = scanner.nextLine();
      Date date= Date.valueOf(setDateStr);
      showAllNeedInfo.showAllOrdersInfoByDate(date);

//      changeNewTransactionMethod.changeGoodsMethod();
//      changeNewTransactionMethod.changeProductMethod();
//      changeNewTransactionMethod.changeOrdersMethod();
//      changeNewTransactionMethod.changeAddressMethod();
//      changeNewTransactionMethod.changeShopsMethod();
//      changeNewTransactionMethod.changeShopsInfoMethod();
//      changeNewTransactionMethod.changeOrdersGoodsMethod();

//      addNewTransactionMethod.addNewProductMethod();
//      addNewTransactionMethod.addNewGoodsMethod();
//      addNewTransactionMethod.addNewOrdersMethod();
//      addNewTransactionMethod.addNewShopMethod();
//      addNewTransactionMethod.addNewAddressMethod();


    }


}

