package com.mkyong.shops.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class ShopsService {

    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);

    public ArrayList<Shops> getAllShops(ShopsDAO shopsDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return shopsDAO.getAllShops();
    }

    public ArrayList<Shops> getAllDeletedShops(ShopsDAO shopsDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return shopsDAO.getAllDeletedShops();
    }

    public void deleteAddress(ShopsDAO shopsDAO,int shopsId) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Delete Shop type | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            shopsDAO.deleteHard(shopsId);
        } else if (deleteType == 2) {
            shopsDAO.deleteSoft(shopsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteAddress(shopsDAO,shopsId);
        }
    }

    public void addNewShops(ShopsDAO shopsDAO) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Set count add Address in shop");
        int addCount = scanner.nextInt();
        String shopName;
        int shopAddressId;
        int shopInfoId;

        for (int i = 0; i < addCount; i++) {
            System.out.println("Count add - " + i + "\n Set shop Name");
            shopName  = scanner1.nextLine();
            System.out.println("Set Shop Address Id where is it");
            shopAddressId  = scanner1.nextInt();
            System.out.println("Set Shop Info Id");
            shopInfoId  = scanner1.nextInt();

            Shops shops = new Shops();
            shops.setShopName(shopName);
            shops.setShopAddressId(shopAddressId);
            shops.setShopInfoId(shopInfoId);
            shops.setCreateDate(new Date(System.currentTimeMillis()));
            shops.setModifyDate(new Date(System.currentTimeMillis()));

            shopsDAO.insert(shops);
        }

    }

    public void changeShops(ShopsDAO shopsDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Address  ID ");
        int shopId = scanner2.nextInt();

        Shops shops =  shopsDAO.findByShopsId(shopId);
        System.out.println(shops.toString());

        System.out.println("---------------- \n What do you change  \n 1- Change Shop name -- 2- Change Shop Address id -- 3- Change Shop Info id  -- 4- Change all  ");

        int change_num = scanner2.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        String shopName = null;
        int shopAddressId = -1;
        int shopInfoId = -1;

        switch (change_num) {

            case 1:
                System.out.println("---------------- \n Set new shop Address ");
                shopName  = scanner1.nextLine();
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new Name City where in shop");
                shopAddressId  = scanner1.nextInt();
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId);
                scanner1.close();
                break;

            case 3:
                System.out.println("---------------- \n Set new Name City where in shop");
                shopInfoId  = scanner1.nextInt();
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId);
                scanner1.close();
                break;

            default:
                System.out.println("---------------- \n Change All \n Set new Shop Name");
                shopName  = scanner1.nextLine();
                System.out.println("Set new Shop Address id ");
                shopAddressId  = scanner1.nextInt();
                System.out.println("Set new Shop info id ");
                shopInfoId  = scanner1.nextInt();
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId);
                scanner1.close();

        }
        scanner2.close();

    }



}
