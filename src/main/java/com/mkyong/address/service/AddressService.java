package com.mkyong.address.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressService {
   Scanner scanner = new Scanner(System.in);
   Scanner scanner1 = new Scanner(System.in);
   Scanner scanner2 = new Scanner(System.in);

   public ArrayList<Address> getAllAddress(AddressDAO addressDAO) throws SQLException, InterruptedException {
       Thread.sleep(500);
       return addressDAO.getAllAddress();
   }

    public ArrayList<Address> getAllDeletedAddress(AddressDAO addressDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return addressDAO.getAllDeletedAddress();
    }

    public void deleteAddress(AddressDAO addressDAO,int addressId) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Delete Address type | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            addressDAO.deleteHard(addressId);
        } else if (deleteType == 2) {
            addressDAO.deleteSoft(addressId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteAddress(addressDAO,addressId);
        }
    }

    public void addNewAddress(AddressDAO addressDAO) throws InterruptedException {
        Thread.sleep(500);
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
            address0.setCreateDate(new Date(System.currentTimeMillis()));
            address0.setModifyDate(new Date(System.currentTimeMillis()));

            addressDAO.insert(address0);
        }

    }
    public void countShopCity(AddressDAO addressDAO) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Set minimum count shop in city ( ? > 0 )| default=0");
        Integer minCountShop  = scanner1.nextInt();

        if(minCountShop < 0 ){
            ArrayList<AddressCountByCity> addressCountByCityList = addressDAO.findCountCity();
            for (AddressCountByCity a :
                    addressCountByCityList) {
                System.out.println("count = " + a.getCount() +" | " + "city = " + a.getCity() );
            }
            scanner1.close();
        } else {
            ArrayList<AddressCountByCity> addressCountByCityList = addressDAO.findCountCity(minCountShop);
            for (AddressCountByCity a :
                    addressCountByCityList) {
                System.out.println("count = " + a.getCount() +" | " + "city = " + a.getCity() );
            }
            scanner1.close();
        }
    }

    public void changeAddress(AddressDAO addressDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Address  ID ");
        int getId = scanner2.nextInt();

        Address address =  addressDAO.findByAddressId(getId);
        System.out.println(address.toString());

        System.out.println("---------------- \n What do you change  \n 1- Change Address -- 2- Change City -- 3- Change all  ");

        int change_num = scanner2.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        String addressName = null;
        String addressCity = null;

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

            default:
                System.out.println("---------------- \n Change All \n Set new shop Address");
                addressName  = scanner1.nextLine();
                System.out.println("Set new Name City where in shop");
                addressCity  = scanner1.nextLine();
                addressDAO.update(addressName,addressCity,getId);
                scanner1.close();

        }
        scanner2.close();

    }




}
