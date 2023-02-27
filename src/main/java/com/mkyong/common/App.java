package com.mkyong.common;

import com.mkyong.methods.AddNewTransactionMethod;
import com.mkyong.methods.ChangeNewTransactionMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

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

        changeNewTransactionMethod.changeGoodsMethod();


//        addNewTransactionMethod.addNewProductMethod();
//        addNewTransactionMethod.addNewGoodsMethod();
//        addNewTransactionMethod.addNewOrdersMethod();
//        addNewTransactionMethod.addNewShopMethod();


    }


}

