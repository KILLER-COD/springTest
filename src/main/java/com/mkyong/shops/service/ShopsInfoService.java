package com.mkyong.shops.service;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.ShopsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ShopsInfoService {
    private final ShopsInfoDAO shopsInfoDAO;
    private final AddressService addressService;

    public List<ShopsInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllShopsInfo();
    }

    public List<ShopsInfo> getAllDeletedShopsInfo() throws SQLException {
        return shopsInfoDAO.getAllDeletedShopsInfo();
    }

    public ShopsInfo findByShopInfoId(int id) throws SQLException {
        return shopsInfoDAO.findByShopsInfoId(id);
    }

    public void deleteShopsInfo(int shopsInfoId) throws SQLException {
        addressService.deleteAddress(findByShopInfoId(shopsInfoId).getAddressId());
        shopsInfoDAO.deleteSoft(shopsInfoId);
    }


    public int addNewShopsInfo(ShopsInfo shopsInfo) {
        shopsInfo.setCreateDate(new Date(System.currentTimeMillis()));
        shopsInfo.setModifyDate(new Date(System.currentTimeMillis()));
        return shopsInfoDAO.insert(shopsInfo, null);
    }


    public void changeShopsInfo(ShopsInfo newShopsInfo, int shopInfoId, Connection conn) throws SQLException {
        newShopsInfo.setAddressId(findByShopInfoId(shopInfoId).getAddressId());
        shopsInfoDAO.update(newShopsInfo, shopInfoId, conn);
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

    public Address getShopsInfoAddressInfo(int shopsInfoAddressId) throws SQLException {
        return addressService.findByAddressId(shopsInfoAddressId);
    }

}
