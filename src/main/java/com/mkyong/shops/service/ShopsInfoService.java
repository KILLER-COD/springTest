package com.mkyong.shops.service;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.shops.dao.ShopInfoDAO;
import com.mkyong.shops.model.ShopInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ShopsInfoService {
    private final ShopInfoDAO shopInfoDAO;
    private final AddressService addressService;

    public List<ShopInfo> getAllShopsInfo() throws SQLException {
        return shopInfoDAO.getAllShopsInfo();
    }

    public List<ShopInfo> getAllDeletedShopsInfo() throws SQLException {
        return shopInfoDAO.getAllDeletedShopsInfo();
    }

    public ShopInfo findByShopInfoId(int id) throws SQLException {
        return shopInfoDAO.findByShopsInfoId(id);
    }

    public void deleteShopsInfo(int shopsInfoId) throws SQLException {
        addressService.deleteAddress(findByShopInfoId(shopsInfoId).getAddressId());
        shopInfoDAO.deleteSoft(shopsInfoId);
    }


    public int addNewShopsInfo(ShopInfo shopInfo) {
        shopInfo.setCreateDate(new Date(System.currentTimeMillis()));
        shopInfo.setModifyDate(new Date(System.currentTimeMillis()));
        return shopInfoDAO.insert(shopInfo, null);
    }


    public void changeShopsInfo(ShopInfo newShopInfo, int shopInfoId, Connection conn) throws SQLException {
        newShopInfo.setAddressId(findByShopInfoId(shopInfoId).getAddressId());
        shopInfoDAO.update(newShopInfo, shopInfoId, conn);
    }


    public void shopsInfoPrint() {
        try {
            shopInfoDAO.getAllShopsInfo().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int shopsInfoId) {
        ShopInfo retInfo = shopInfoDAO.findByShopsInfoId(shopsInfoId);
        return retInfo != null;
    }

    public Address getShopsInfoAddressInfo(int shopsInfoAddressId) throws SQLException {
        return addressService.findByAddressId(shopsInfoAddressId);
    }

}
