package com.mkyong.shops.service;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.ShopAllData;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.model.ShopsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ShopsService {
    private final ShopsDAO shopsDAO;
    private final AddressService addressService;
    private final ShopsInfoService shopsInfoService;
//    private final ShopContactingInfo contactingInfo;

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
        Shops shops = findByShopsId(shopsId);
        shopsInfoService.deleteShopsInfo(shops.getShopInfoId());
        addressService.deleteAddress(shops.getShopAddressId());
        shopsDAO.deleteSoft(shopsId);
    }

    public int addNewShops(ShopAllData shopAllData, Connection conn) {
        Address shopInfoAddress = Address.builder()
                .address(shopAllData.getShopInfoAddress())
                .city(shopAllData.getShopInfoCity())
                .build();
        Address shopAddress = Address.builder()
                .address(shopAllData.getShopAddress())
                .city(shopAllData.getShopCity())
                .build();
        int shopAddressId = addressService.addNewAddress(shopAddress);
        int shopInfoAddressId = addressService.addNewAddress(shopInfoAddress);

        ShopsInfo shopsInfo = ShopsInfo.builder()
                .shopOwner(shopAllData.getShopOwner())
                .hvhh(shopAllData.getHvhh())
                .addressId(shopInfoAddressId)
                .build();
        int newShopsInfoId = shopsInfoService.addNewShopsInfo(shopsInfo);

        Shops shops = Shops.builder()
                .shopName(shopAllData.getShopName())
                .shopAddressId(shopAddressId)
                .shopInfoId(newShopsInfoId)
                .createDate(new Date(System.currentTimeMillis()))
                .modifyDate(new Date(System.currentTimeMillis()))
                .build();
        return shopsDAO.insert(shops, conn);
    }

    public void changeShops(ShopAllData shopAllData, int shopsId, Connection conn) throws SQLException {
        shopsDAO.update(checkedUpdateShops(shopAllData, shopsId), conn);
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

    public Shops checkedUpdateShops(ShopAllData shopAllData, int shopsId) throws SQLException {
        //Checked Change Shops name -------------------------------------------------
        Shops shops = findByShopsId(shopsId);

        if (!Objects.equals(shops.getShopName(), shopAllData.getShopName())) {
            shops.setShopName(shopAllData.getShopName());
        }

        Address shopInfoAddress = Address.builder()
                .address(shopAllData.getShopInfoAddress())
                .city(shopAllData.getShopInfoCity())
                .build();
        Address shopAddress = Address.builder()
                .address(shopAllData.getShopAddress())
                .city(shopAllData.getShopCity())
                .build();
        ShopsInfo shopsInfo = ShopsInfo.builder()
                .shopOwner(shopAllData.getShopOwner())
                .hvhh(shopAllData.getHvhh())
                .build();

        int addressShopsId = shops.getShopAddressId();
        addressService.changeAddress(shopAddress, addressShopsId);

        int addressShopsInfoId = shopsInfoService.findByShopInfoId(shops.getShopInfoId()).getAddressId();
        addressService.changeAddress(shopInfoAddress, addressShopsInfoId);

        shopsInfoService.changeShopsInfo(shopsInfo, shops.getShopInfoId(), null);
        return shops;
    }

    public List<ShopsInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoService.getAllShopsInfo();
    }

}
