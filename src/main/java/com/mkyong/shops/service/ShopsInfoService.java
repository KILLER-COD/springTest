package com.mkyong.shops.service;

import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.ShopsInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ShopsInfoService {
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);

    public ArrayList<ShopsInfo> getAllShopsInfo(ShopsInfoDAO shopsInfoDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return shopsInfoDAO.getAllShopsInfo();
    }

    public ArrayList<ShopsInfo> getAllDeletedShops(ShopsInfoDAO shopsInfoDAO) throws SQLException, InterruptedException {
        Thread.sleep(500);
        return shopsInfoDAO.getAllDeletedShopsInfo();
    }

    public void deleteAddress(ShopsInfoDAO shopsInfoDAO,int shopsId) throws SQLException, InterruptedException {
        Thread.sleep(500);
        System.out.println("Set Delete Shop Info type | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            shopsInfoDAO.deleteHard(shopsId);
        } else if (deleteType == 2) {
            shopsInfoDAO.deleteSoft(shopsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteAddress(shopsInfoDAO,shopsId);
        }
    }

    public void addNewShops(ShopsInfoDAO shopsInfoDAO) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Set count add Address in shop");
        int addCount = scanner.nextInt();
        String shopOwner;
        int hvhh;
        int addressId;

        for (int i = 0; i < addCount; i++) {
            System.out.println("Count add - " + i + "\n Set Shop Owner Name or Group Name");
            shopOwner  = scanner1.nextLine();
            System.out.println("Set Shop HVHH ");
            hvhh  = scanner1.nextInt();
            System.out.println("Set Shop Info Address Id");
            addressId  = scanner1.nextInt();

            ShopsInfo shopsInfo = new ShopsInfo();
           shopsInfo.setShopOwner(shopOwner);
           shopsInfo.setHvhh(hvhh);
           shopsInfo.setAddressId(addressId);
           shopsInfo.setCreateDate(new Date(System.currentTimeMillis()));
           shopsInfo.setModifyDate(new Date(System.currentTimeMillis()));

            shopsInfoDAO.insert(shopsInfo);
        }

    }

    public void changeShops(ShopsInfoDAO shopsInfoDAO) throws SQLException, InterruptedException {

        ArrayList<ShopsInfo> shopsInfoList =  shopsInfoDAO.getAllShopsInfo();
        for (ShopsInfo shopsInfo : shopsInfoList ) {
            System.out.println(shopsInfo);
        }

        Thread.sleep(500);
        System.out.println("---------------- \n Set Shop Info  ID ");
        int shopInfoId = scanner2.nextInt();

        ShopsInfo shopsInfo = shopsInfoDAO.findByShopsInfoId(shopInfoId);
        System.out.println(shopsInfo.toString());

        System.out.println("---------------- \n What do you change  \n 1- Change Shop name -- 2- Change Shop Address id -- 3- Change Shop Info id  -- 4- Change all  ");

        int change_num = scanner2.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        String shopOwner = null;
        int hvhh = -1;
        int addressId = -1;

        switch (change_num) {

            case 1:
                System.out.println("---------------- \n Set new Shop Owner ");
                shopOwner = scanner1.nextLine();
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new Name City where in shop");
                hvhh = scanner1.nextInt();
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId);
                scanner1.close();
                break;

            case 3:
                System.out.println("---------------- \n Set new Shop Address id");
                shopInfoId = scanner1.nextInt();
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId);
                scanner1.close();
                break;

            default:
                System.out.println("---------------- \n Change All \n Set new Shop Owner ");
                shopOwner = scanner1.nextLine();
                System.out.println("Set new Shop HVHH ");
                hvhh = scanner1.nextInt();
                System.out.println("Set new Shop info id ");
                shopInfoId = scanner1.nextInt();
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId);
                scanner1.close();

        }
        scanner2.close();
    }

}
