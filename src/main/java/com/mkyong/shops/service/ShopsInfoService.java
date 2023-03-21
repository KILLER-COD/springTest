package com.mkyong.shops.service;

import com.mkyong.address.model.Address;
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


    public void changeShopsInfo(ShopsInfo newShopsInfo, int shopInfoId, Connection conn) throws SQLException {
        shopsInfoDAO.update(checkedUpdateShops(newShopsInfo, shopInfoId), shopInfoId, conn);
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

    public ShopsInfo checkedUpdateShops(ShopsInfo newShopsInfo, int shopsInfoId) throws SQLException {
        // Checked Change Shops Info and Shops Info Address ------------------------
        ShopsInfo shopsInfo = findByShopInfoId(shopsInfoId);

        if (!shopsInfo.getShopOwner().equals(newShopsInfo.getShopOwner())) {
            shopsInfo.setShopOwner(newShopsInfo.getShopOwner());
        }
        if (shopsInfo.getHvhh() != newShopsInfo.getHvhh()) {
            shopsInfo.setHvhh(newShopsInfo.getHvhh());
        }
        return shopsInfo;
    }

    public Address getShopsInfoAddressInfo(int shopsInfoAddressId) throws SQLException {
        return addressService.findByAddressId(shopsInfoAddressId);
    }

//    public NewShopInfo getShowShopsInfoData(int shopsInfoId) throws SQLException {
//        ShopsInfo shopsInfo = findByShopInfoId(shopsInfoId);
//        Address address = addressService.findByAddressId(shopsInfo.getAddressId());
//        return NewShopInfo.builder()
//                .shopsInfo(shopsInfo)
//                .address(address)
//                .build();
//
//    }
//
//
//    public void setNewShopsInfoData(NewShopInfo newShopInfo) {
//        Address address = newShopInfo.getAddress();
//        int addressId = addressService.addNewAddress(address);
//
//        ShopsInfo shopsInfo = newShopInfo.getShopsInfo();
//        shopsInfo.setAddressId(addressId);
//        addNewShopsInfo(shopsInfo);
//    }

}
