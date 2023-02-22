package com.mkyong.address.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.mkyong.common.App.sqlDate;

public class AddressService {
   static Scanner scanner = new Scanner(System.in);
    static Scanner scanner1 = new Scanner(System.in);
   static Scanner scanner2 = new Scanner(System.in);

   public ArrayList<Address> getAllAddress(AddressDAO addressDAO) throws SQLException {
       return addressDAO.getAllAddress();
   }

    public void deleteAddress(AddressDAO addressDAO,int address_id) throws SQLException {
        addressDAO.delete(address_id);
    }

    public void addNewAddress(AddressDAO addressDAO){

        System.out.println("Set count add Address in shop");
        int addCount = scanner.nextInt();

        for (int i = 0; i < addCount; i++) {
            System.out.println("Count add - " + i);
            System.out.println("Set shop Address");
            String address_name  = scanner1.nextLine();
            System.out.println("Set Name City where in shop");
            String address_city  = scanner1.nextLine();

            Address address0 = new Address();
            address0.setAddress(address_name);
            address0.setCity(address_city);
            address0.setCreateDate(sqlDate);
            address0.setModifyDate(sqlDate);

            addressDAO.insert(address0);
        }

    }

    public void changeAddress(AddressDAO addressDAO) throws SQLException, InterruptedException {

        Thread.sleep(1000);
        System.out.println("Set Address  ID ");
        int getId = scanner2.nextInt();

        Address address =  addressDAO.findByAddressId(getId);
        System.out.println(address.toString());
        System.out.println("----------------");

        System.out.println("What do you change  \n 1- Change Address -- 2- Change City -- 3- Change all  ");

        int change_num = scanner2.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        String addressName = null;
        String addressCity = null;
        String str;
        switch (change_num) {

            case 1:
                System.out.println("---------------- \n Set new shop Address ");
                addressName  = scanner1.nextLine();
                addressDAO.update(addressName,addressCity,getId);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new Name City where in shop");
                addressCity  = scanner1.nextLine();
                addressDAO.update(addressName,addressCity,getId);
                scanner1.close();
                break;
            case 3:
                str  = scanner1.nextLine();
                System.out.println("Count of "+ str + "--" + addressDAO.findCountCity());
                scanner1.close();
                break;

            default:
                System.out.println("---------------- \n Change All \n Set new shop Address");
                addressName  = scanner1.nextLine();
                System.out.println("Set new Name City where in shop");
                addressCity  = scanner1.nextLine();
                addressDAO.update(addressName,addressCity,getId);
                scanner1.close();

        }

    }




}
