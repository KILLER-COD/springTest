package com.mkyong.shops.service;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.GetShopAllData;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.model.ShopsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

    public List<Shops> getAllShops() throws SQLException {
        return shopsDAO.getAllShops();
    }

    public List<Shops> getAllDeletedShops() throws SQLException {
        return shopsDAO.getAllDeletedShops();
    }

    public Shops findByShopsId(int id) throws SQLException {
        return shopsDAO.findByShopsId(id);
    }

    public void deleteShops(int shopsId) throws SQLException {
        shopsDAO.deleteSoft(shopsId);
    }

    public int addNewShops(GetShopAllData getShopAllData, Connection conn) {
        Address shopInfoAddress = Address.builder()
                .address(getShopAllData.getShopInfoAddress())
                .city(getShopAllData.getShopInfoCity())
                .build();
        Address shopAddress = Address.builder()
                .address(getShopAllData.getShopAddress())
                .city(getShopAllData.getShopCity())
                .build();
        int shopAddressId = addressService.addNewAddress(shopAddress);
        int shopInfoAddressId = addressService.addNewAddress(shopInfoAddress);

        ShopsInfo shopsInfo = ShopsInfo.builder()
                .shopOwner(getShopAllData.getShopOwner())
                .hvhh(getShopAllData.getHvhh())
                .addressId(shopInfoAddressId)
                .build();
        int newShopsInfoId = shopsInfoService.addNewShopsInfo(shopsInfo);

        Shops shops = Shops.builder()
                .shopName(getShopAllData.getShopName())
                .shopAddressId(shopAddressId)
                .shopInfoId(newShopsInfoId)
                .createDate(new Date(System.currentTimeMillis()))
                .modifyDate(new Date(System.currentTimeMillis()))
                .build();
        return shopsDAO.insert(shops, conn);
    }

    public void changeShops(GetShopAllData getShopAllData, int shopsId, Connection conn) throws SQLException {
        shopsDAO.update(checkedUpdateShops(getShopAllData, shopsId), conn);
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

    public Shops checkedUpdateShops(GetShopAllData getShopAllData, int shopsId) throws SQLException {
        //Checked Change Shops name -------------------------------------------------
        Shops shops = findByShopsId(shopsId);

        if (!Objects.equals(shops.getShopName(), getShopAllData.getShopName())) {
            shops.setShopName(getShopAllData.getShopName());
        }

        Address shopInfoAddress = Address.builder()
                .address(getShopAllData.getShopInfoAddress())
                .city(getShopAllData.getShopInfoCity())
                .build();
        Address shopAddress = Address.builder()
                .address(getShopAllData.getShopAddress())
                .city(getShopAllData.getShopCity())
                .build();
        ShopsInfo shopsInfo = ShopsInfo.builder()
                .shopOwner(getShopAllData.getShopOwner())
                .hvhh(getShopAllData.getHvhh())
                .build();

        int addressShopsId = shops.getShopAddressId();
        Address addressShop = addressService.checkedUpdateAddress(shopAddress, addressShopsId);
        addressService.changeAddress(addressShop, addressShopsId);

        int addressShopsInfoId = shopsInfoService.findByShopInfoId(shops.getShopInfoId()).getAddressId();
        Address addressShopInfo = addressService.checkedUpdateAddress(shopInfoAddress, addressShopsInfoId);
        addressService.changeAddress(addressShopInfo, addressShopsInfoId);

        shopsInfoService.changeShopsInfo(shopsInfo, shops.getShopInfoId(), null);
        return shops;
    }

    public List<ShopsInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoService.getAllShopsInfo();
    }

}
