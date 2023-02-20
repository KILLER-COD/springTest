package com.mkyong.common;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");

        java.util.Date myDate = new java.util.Date("10/10/2009");
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        Address address = new Address();
        address.setAddress("Halidzor");
        address.setCity("Kapan");
        address.setCreate_date(sqlDate);
        address.setModify_date(sqlDate);

        addressDAO.insert(address);

        Address address1 = addressDAO.findByAddressId(1);
        System.out.println(address1.toString());

    }
}