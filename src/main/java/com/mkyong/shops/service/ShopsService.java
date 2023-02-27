package com.mkyong.shops.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

@Component
public class ShopsService {

    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    @Autowired
    private ShopsDAO shopsDAO;

    public ArrayList<Shops> getAllShops() throws SQLException {
        return shopsDAO.getAllShops();
    }

    public ArrayList<Shops> getAllDeletedShops() throws SQLException {
        return shopsDAO.getAllDeletedShops();
    }

    public void deleteShops(int shopsId) throws SQLException {
        System.out.println("Set Delete Shop type | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            shopsDAO.deleteHard(shopsId);
        } else if (deleteType == 2) {
            shopsDAO.deleteSoft(shopsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteShops(shopsId);
        }
    }

    public int addNewShops(Connection conn,int addressIdShop,int shopsInfoId){
        String shopName;

        System.out.println("Set shop Name");
        shopName  = scanner1.nextLine();

            Shops shops = new Shops();
            shops.setShopName(shopName);
            shops.setShopAddressId(addressIdShop);
            shops.setShopInfoId(shopsInfoId);
            shops.setCreateDate(new Date(System.currentTimeMillis()));
            shops.setModifyDate(new Date(System.currentTimeMillis()));

       return shopsDAO.insert(shops,conn);

    }

    public void changeShops(Connection conn) throws SQLException {
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
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId,conn);
                scanner1.close();
                break;

            case 2:
                System.out.println("---------------- \n Set new Name City where in shop");
                shopAddressId  = scanner1.nextInt();
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId,conn);
                scanner1.close();
                break;

            case 3:
                System.out.println("---------------- \n Set new Name City where in shop");
                shopInfoId  = scanner1.nextInt();
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId,conn);
                scanner1.close();
                break;

            default:
                System.out.println("---------------- \n Change All \n Set new Shop Name");
                shopName  = scanner1.nextLine();
                System.out.println("Set new Shop Address id ");
                shopAddressId  = scanner1.nextInt();
                System.out.println("Set new Shop info id ");
                shopInfoId  = scanner1.nextInt();
                shopsDAO.update(shopName,shopAddressId,shopInfoId,shopId,conn);
                scanner1.close();

        }
        scanner2.close();

    }

    public void shopsPrint(){
        try {
            for (Shops shops : getAllShops()) {
                System.out.println(shops.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
