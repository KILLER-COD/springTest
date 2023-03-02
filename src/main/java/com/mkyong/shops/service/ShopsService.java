package com.mkyong.shops.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.address.service.AddressService;
import com.mkyong.orders.model.Orders;
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
    @Autowired
    private AddressService addressService;
    @Autowired
    private ShopsInfoService shopsInfoService;

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
        shopName  = scanner1.next();

            Shops shops = new Shops();
            shops.setShopName(shopName);
            shops.setShopAddressId(addressIdShop);
            shops.setShopInfoId(shopsInfoId);
            shops.setCreateDate(new Date(System.currentTimeMillis()));
            shops.setModifyDate(new Date(System.currentTimeMillis()));

       return shopsDAO.insert(shops,conn);

    }

    public void changeShops(Connection conn) throws SQLException {
        int shopAddressId;
        int shopInfoId;
        String shopName;
        shopsPrint();
        System.out.println("Set Shop  ID ");
        int shopId = scanner2.nextInt();

        Shops shops = shopsDAO.findByShopsId(shopId);
        System.out.println(shops.toString());

        System.out.println("----------------  Set new shop Address ");
        shopName = scanner1.nextLine();

        addressService.getAllAddress().forEach(System.out::println);
        System.out.println("---------------- \n Set new Name City where in shop");
        shopAddressId = scanner1.nextInt();

        shopsInfoService.getAllShopsInfo().forEach(System.out::println);
        System.out.println("---------------- \n Set new shop Info ID");
        shopInfoId = scanner1.nextInt();

        shops.setShopName(shopName);
        shops.setShopAddressId(shopAddressId);
        shops.setShopInfoId(shopInfoId);

        shopsDAO.update(shops,shopId ,conn);

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

    public boolean existsById(int shopsId){
        Shops retInfo = shopsDAO.findByShopsId(shopsId);
        if (retInfo == null){
            return false;
        }
        return true;
    }


}
