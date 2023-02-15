package com.mkyong.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;

public class App {
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        Customer customer = new Customer();
        customer.setAge(28);
        customer.setName("Artur");

        customerDAO.insert(customer);

        Customer customer1 = customerDAO.findByCustomerId(2);
        System.out.println(customer1.toString());

    }
}