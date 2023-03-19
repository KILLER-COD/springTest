package com.mkyong.shops.service;

import com.mkyong.address.service.AddressService;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ShopsService {
    @Autowired
    private ConsoleInputService consoleInputService;
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

    public Shops findByShopsId(int id) throws SQLException {
        return shopsDAO.findByShopsId(id);
    }

    public void deleteShops(int shopsId) throws SQLException {
        System.out.println("Set Delete Shop type | 1 - hard | 2 - Soft");
        int deleteType = consoleInputService.readInt();
        if (deleteType == 1) {
            shopsDAO.deleteHard(shopsId);
        } else if (deleteType == 2) {
            shopsDAO.deleteSoft(shopsId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteShops(shopsId);
        }
    }

    public int addNewShops(Shops shops, Connection conn) {
        shops.setCreateDate(new Date(System.currentTimeMillis()));
        shops.setModifyDate(new Date(System.currentTimeMillis()));
        return shopsDAO.insert(shops, conn);

    }

    public void changeShops(Connection conn) throws SQLException {

        shopsPrint();
        System.out.println("Set Shop  ID ");
        int shopId = consoleInputService.readInt();

        Shops shops = shopsDAO.findByShopsId(shopId);
        System.out.println(shops.toString());

        System.out.println("----------------  Set new shop Address ");
        String shopName = consoleInputService.readString();

        addressService.getAllAddress().forEach(System.out::println);
        System.out.println("---------------- \n Set new Name City where in shop");
        int shopAddressId = consoleInputService.readInt();

        shopsInfoService.getAllShopsInfo().forEach(System.out::println);
        System.out.println("---------------- \n Set new shop Info ID");
        int shopInfoId = consoleInputService.readInt();

        shops.setShopName(shopName);
        shops.setShopAddressId(shopAddressId);
        shops.setShopInfoId(shopInfoId);

        shopsDAO.update(shops, shopId, conn);

    }

    public void shopsPrint() {
        try {
            shopsDAO.getAllShops().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int shopsId) {
        Shops retInfo = shopsDAO.findByShopsId(shopsId);
        return retInfo != null;
    }


}
