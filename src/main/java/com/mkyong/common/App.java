package com.mkyong.common;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.address.service.AddressService;
import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.dao.ShopsInfoDao;
import com.mkyong.shops.model.Shops;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {

    static long dateCurrent =System.currentTimeMillis();
    public static java.sql.Date sqlDate = new java.sql.Date(dateCurrent);
    public static void main( String[] args ) throws SQLException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");


        AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
        GoodsDAO goodsDAO = (GoodsDAO) context.getBean("goodsDAO");
        OrdersDAO ordersDAO = (OrdersDAO) context.getBean("ordersDAO");
        OrdersGoodsDAO ordersGoodsDAO = (OrdersGoodsDAO) context.getBean("ordersGoodsDAO");
        ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
        ShopsDAO shopsDAO = (ShopsDAO) context.getBean("shopsDAO");
        ShopsInfoDao shopsInfoDAO = (ShopsInfoDao) context.getBean("shopsInfoDAO");


        AddressService addressService = new AddressService();

        ArrayList<Address> arrayList = addressService.getAllAddress(addressDAO);

        for (Address address : arrayList ) {
            System.out.println(address);
        }

        ArrayList<AddressCountByCity> addressCountByCitie = addressDAO.findCountCity();

        for (AddressCountByCity a :
                addressCountByCitie) {
            System.out.println("count = " + a.getCount() +" | " + "city = " + a.getCity() );
        }

//        System.out.println("----------------");
//        addressService.changeAddress(addressDAO);




//        addressService.addNewAddress(addressDAO);
//        System.out.println("----------------");
//        ArrayList<Address> arrayList0 = addressService.getAllAddress(addressDAO);
//
//        for (Address address : arrayList0 ) {
//            System.out.println(address);
//        }
//        System.out.println("----------------");

//        ArrayList<Address> arrayList1 = addressService.getAllAddress(addressDAO);

//        for (Address address : arrayList1 ) {
//            System.out.println(address);
//        }
//        System.out.println("----------------");
//        addressService.deleteAddress(addressDAO,14);
//        System.out.println("----------------");
//        ArrayList<Address> arrayList3 = addressService.getAllAddress(addressDAO);
//
//        for (Address address : arrayList3 ) {
//            System.out.println(address);
//        }

    }



}

