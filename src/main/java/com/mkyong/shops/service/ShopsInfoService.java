package com.mkyong.shops.service;

import com.mkyong.address.service.AddressService;
import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.ShopsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class ShopsInfoService {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private ShopsInfoDAO shopsInfoDAO;

    @Autowired
    private AddressService addressService;

    public ArrayList<ShopsInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllShopsInfo();
    }

    public ArrayList<ShopsInfo> getAllDeletedShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllDeletedShopsInfo();
    }

    public void deleteShopsInfo(int shopsId) throws SQLException {
        System.out.println("Set Delete Shop Info type | 1 - hard | 2 - Soft");
        int deleteType = scanner.nextInt();
        if (deleteType == 1) {
            shopsInfoDAO.deleteHard(shopsId);
        } else if (deleteType == 2) {
            shopsInfoDAO.deleteSoft(shopsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteShopsInfo(shopsId);
        }
    }

    public int addNewShopsInfo(Connection conn, int addressId) throws InterruptedException {
        Thread.sleep(500);
        String shopOwner;
        int hvhh;

        System.out.println("Set Shop Owner Name or Group Name");
        shopOwner = scanner.nextLine();
        System.out.println("Set Shop HVHH ");
        hvhh = scanner.nextInt();

        ShopsInfo shopsInfo = new ShopsInfo();
        shopsInfo.setShopOwner(shopOwner);
        shopsInfo.setHvhh(hvhh);
        shopsInfo.setAddressId(addressId);
        shopsInfo.setCreateDate(new Date(System.currentTimeMillis()));
        shopsInfo.setModifyDate(new Date(System.currentTimeMillis()));

        return shopsInfoDAO.insert(shopsInfo, conn);

    }

    public void changeShopsInfo(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        shopsInfoPrint();
        System.out.println("----------------  Set Shop Info  ID ");
        int shopInfoId = scanner.nextInt();
        while (!existsById(shopInfoId)) {
            System.out.println("----------------  Set Shop Info  ID ");
            shopInfoId = scanner.nextInt();
        }

        ShopsInfo shopsInfo = shopsInfoDAO.findByShopsInfoId(shopInfoId);
        System.out.println(shopsInfo.toString());
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("---------------- Set new Shop Owner = " + shopsInfo.getShopOwner());
        String shopOwner = scanner1.nextLine();
        while (shopOwner.equals("")) {
            System.out.println("---------------- Set Correct Shop Owner =" + shopsInfo.getShopOwner());
            shopOwner = scanner1.nextLine();
        }

        System.out.println("----------------  Set new HVHH in shop = " + shopsInfo.getHvhh());
        int hvhh = scanner1.nextInt();
        while (hvhh < 0) {
            System.out.println("---------------- Set Correct  HVHH in shop =" + shopsInfo.getHvhh());
            hvhh = scanner1.nextInt();
        }

        addressService.addressPrint();
        System.out.println("----------------  Set new Shop Address id = " + shopsInfo.getAddressId());
        int addressId = scanner1.nextInt();
        while (!addressService.existsById(addressId) && addressId > 0) {
            System.out.println("----------------  Set new Shop Address id = " + shopsInfo.getAddressId());
            addressId = scanner1.nextInt();
        }

        shopsInfo.setShopOwner(shopOwner);
        shopsInfo.setHvhh(hvhh);
        shopsInfo.setAddressId(addressId);

        System.out.println(shopsInfo.toString());
        shopsInfoDAO.update(shopsInfo, shopInfoId, conn);

        scanner.close();
    }

    public void shopsInfoPrint() {
        try {
            ArrayList<ShopsInfo> shopsInfoList = shopsInfoDAO.getAllShopsInfo();
            for (ShopsInfo shopsInfo : shopsInfoList) {
                System.out.println(shopsInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int shopsInfoId) {
        ShopsInfo retInfo = shopsInfoDAO.findByShopsInfoId(shopsInfoId);
        if (retInfo == null) {
            return false;
        }
        return true;
    }

}
