package com.mkyong.shops.service;

import com.mkyong.address.service.AddressService;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.ShopsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShopsInfoService {
    @Autowired
    private ShopsInfoDAO shopsInfoDAO;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ConsoleInputService consoleInputService;

    public List<ShopsInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllShopsInfo();
    }

    public List<ShopsInfo> getAllDeletedShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllDeletedShopsInfo();
    }

    public ShopsInfo findByShopInfoId(int id) throws SQLException {
        return shopsInfoDAO.findByShopsInfoId(id);
    }

    public void deleteShopsInfo(int shopsId) throws SQLException {
        shopsInfoDAO.deleteSoft(shopsId);
    }


    public int addNewShopsInfo(ShopsInfo shopsInfo) {
        shopsInfo.setCreateDate(new Date(System.currentTimeMillis()));
        shopsInfo.setModifyDate(new Date(System.currentTimeMillis()));
        return shopsInfoDAO.insert(shopsInfo, null);
    }


    public void changeShopsInfo(ShopsInfo shopsInfo, int shopInfoId, Connection conn) throws SQLException {
        shopsInfoDAO.update(shopsInfo, shopInfoId, conn);
    }


    public void shopsInfoPrint() {
        try {
            shopsInfoDAO.getAllShopsInfo().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int shopsInfoId) {
        ShopsInfo retInfo = shopsInfoDAO.findByShopsInfoId(shopsInfoId);
        return retInfo != null;
    }


    //    public void deleteShopsInfo(int shopsId) throws SQLException {
//        System.out.println("Set Delete Shop Info type | 1 - hard | 2 - Soft");
//        int deleteType = consoleInputService.readInt();
//        if (deleteType == 1) {
//            shopsInfoDAO.deleteHard(shopsId);
//        } else if (deleteType == 2) {
//            shopsInfoDAO.deleteSoft(shopsId);
//        } else {
//            System.out.println("Error : Set correct Number ");
//            deleteShopsInfo(shopsId);
//        }
//    }

    //    public int addNewShopsInfo(Connection conn, int addressId)  {
//
//        System.out.println("Set Shop Owner Name or Group Name");
//        String shopOwner = consoleInputService.readString();
//        System.out.println("Set Shop HVHH ");
//        int hvhh = consoleInputService.readInt();
//
//        ShopsInfo shopsInfo = new ShopsInfo();
//        shopsInfo.setShopOwner(shopOwner);
//        shopsInfo.setHvhh(hvhh);
//        shopsInfo.setAddressId(addressId);
//        shopsInfo.setCreateDate(new Date(System.currentTimeMillis()));
//        shopsInfo.setModifyDate(new Date(System.currentTimeMillis()));
//
//        return shopsInfoDAO.insert(shopsInfo, conn);
//
//    }

    //    public void changeShopsInfo(Connection conn) throws SQLException {
//
//        shopsInfoPrint();
//        System.out.println("----------------  Set Shop Info  ID ");
//        int shopInfoId = consoleInputService.readInt();
//        while (!existsById(shopInfoId)) {
//            System.out.println("----------------  Set Shop Info  ID ");
//            shopInfoId = consoleInputService.readInt();
//        }
//
//        ShopsInfo shopsInfo = shopsInfoDAO.findByShopsInfoId(shopInfoId);
//        System.out.println(shopsInfo.toString());
//        System.out.println("---------------- Set new Shop Owner = " + shopsInfo.getShopOwner());
//        String shopOwner = consoleInputService.readString();
//        while (shopOwner.equals("")) {
//            System.out.println("---------------- Set Correct Shop Owner =" + shopsInfo.getShopOwner());
//            shopOwner = consoleInputService.readString();
//        }
//
//        System.out.println("----------------  Set new HVHH in shop = " + shopsInfo.getHvhh());
//        Integer hvhh = consoleInputService.readInt();
//        while (hvhh < 0) {
//            System.out.println("---------------- Set Correct  HVHH in shop =" + shopsInfo.getHvhh());
//            hvhh = consoleInputService.readInt();
//        }
//
////        addressService.addressPrint();
//        System.out.println("----------------  Set new Shop Address id = " + shopsInfo.getAddressId());
//        int addressId = consoleInputService.readInt();
//        while (!addressService.existsById(addressId)) {
//            System.out.println("----------------  Set new Shop Address id = " + shopsInfo.getAddressId());
//            addressId = consoleInputService.readInt();
//        }
//
//        shopsInfo.setShopOwner(shopOwner);
//        shopsInfo.setHvhh(hvhh);
//        shopsInfo.setAddressId(addressId);
//
//        System.out.println(shopsInfo.toString());
//        shopsInfoDAO.update(shopsInfo, shopInfoId, conn);
//
//    }


}
