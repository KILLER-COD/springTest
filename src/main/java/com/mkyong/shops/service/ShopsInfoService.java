package com.mkyong.shops.service;

import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.ShopsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;
@Component
public class ShopsInfoService {
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    @Autowired
    private ShopsInfoDAO shopsInfoDAO;

    public ArrayList<ShopsInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllShopsInfo();
    }

    public ArrayList<ShopsInfo> getAllDeletedShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllDeletedShopsInfo();
    }

    public void deleteShopsInfo(int shopsId) throws SQLException {
        System.out.println("Set Delete Shop Info type | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            shopsInfoDAO.deleteHard(shopsId);
        } else if (deleteType == 2) {
            shopsInfoDAO.deleteSoft(shopsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteShopsInfo(shopsId);
        }
    }

    public int addNewShopsInfo(Connection conn,int addressId) throws InterruptedException {
        Thread.sleep(500);
        String shopOwner;
        int hvhh;

            System.out.println("Set Shop Owner Name or Group Name");
            shopOwner  = scanner1.nextLine();
            System.out.println("Set Shop HVHH ");
            hvhh  = scanner1.nextInt();

            ShopsInfo shopsInfo = new ShopsInfo();
            shopsInfo.setShopOwner(shopOwner);
            shopsInfo.setHvhh(hvhh);
            shopsInfo.setAddressId(addressId);
            shopsInfo.setCreateDate(new Date(System.currentTimeMillis()));
            shopsInfo.setModifyDate(new Date(System.currentTimeMillis()));

           return shopsInfoDAO.insert(shopsInfo,conn);

    }

    public void changeShopsInfo( Connection conn) throws SQLException{

        shopsInfoPrint();

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
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId,conn);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new Name City where in shop");
                hvhh = scanner1.nextInt();
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId, conn);
                scanner1.close();
                break;

            case 3:
                System.out.println("---------------- \n Set new Shop Address id");
                shopInfoId = scanner1.nextInt();
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId ,conn);
                scanner1.close();
                break;

            default:
                System.out.println("---------------- \n Change All \n Set new Shop Owner ");
                shopOwner = scanner1.nextLine();
                System.out.println("Set new Shop HVHH ");
                hvhh = scanner1.nextInt();
                System.out.println("Set new Shop info id ");
                shopInfoId = scanner1.nextInt();
                shopsInfoDAO.update(shopOwner, hvhh, addressId, shopInfoId,conn);
                scanner1.close();

        }
        scanner2.close();
    }

    public void shopsInfoPrint(){
        try {
            ArrayList<ShopsInfo> shopsInfoList = shopsInfoDAO.getAllShopsInfo();
            for (ShopsInfo shopsInfo : shopsInfoList) {
                System.out.println(shopsInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
