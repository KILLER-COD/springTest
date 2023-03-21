package com.mkyong.shops.service;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.AllShopsData;
import com.mkyong.shops.model.NewShopInfo;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.model.ShopsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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

    public int addNewShops(AllShopsData allShopsData, Connection conn) {
        ShopsInfo shopsInfo = allShopsData.getNewShopInfo().getShopsInfo();
        int shopAddressId = addressService.addNewAddress(allShopsData.getAddressShop());
        int shopInfoAddressId = addressService.addNewAddress(allShopsData.getNewShopInfo().getAddress());
        shopsInfo.setAddressId(shopInfoAddressId);
        allShopsData.getNewShopInfo().setShopsInfo(shopsInfo);
        int newShopsInfoId = shopsInfoService.addNewShopsInfo(shopsInfo);
        Shops shops = Shops.builder()
                .shopName(allShopsData.getShops().getShopName())
                .shopAddressId(shopAddressId)
                .shopInfoId(newShopsInfoId)
                .createDate(new Date(System.currentTimeMillis()))
                .modifyDate(new Date(System.currentTimeMillis()))
                .build();
        return shopsDAO.insert(shops, conn);
    }

    public void changeShops(AllShopsData allShopsData, int shopsId, Connection conn) throws SQLException {
        shopsDAO.update(checkedUpdateShops(allShopsData, shopsId), conn);
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

    public AllShopsData getShowShopsData(int shopsId) throws SQLException {
        Shops shops = findByShopsId(shopsId);
        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(shops.getShopInfoId());
        NewShopInfo newShopInfo = NewShopInfo.builder()
                .shopsInfo(shopsInfo)
                .address(addressService.findByAddressId(shopsInfo.getAddressId()))
                .build();

        return AllShopsData.builder()
                .shops(shops)
                .addressShop(addressService.findByAddressId(shops.getShopAddressId()))
                .newShopInfo(newShopInfo)
                .build();
    }

    public Shops checkedUpdateShops(AllShopsData allShopsData, int shopsId) throws SQLException {
        //Checked Change Shops name -------------------------------------------------
        Shops shops = findByShopsId(shopsId);
        Shops shopsNew = allShopsData.getShops();
        if (!shops.getShopName().equals(shopsNew.getShopName())) {
            shops.setShopName(shopsNew.getShopName());
        }

        int addressShopsId = shops.getShopAddressId();
        Address addressShop = addressService.checkedUpdateAddress(allShopsData.getAddressShop(), addressShopsId);
        addressService.changeAddress(addressShop, shops.getShopAddressId());

        int addressShopsInfoId = shopsInfoService.findByShopInfoId(shops.getShopInfoId()).getAddressId();
        Address addressShopInfo = addressService.checkedUpdateAddress(allShopsData.getNewShopInfo().getAddress(), addressShopsInfoId);
        addressService.changeAddress(addressShopInfo, addressShopsInfoId);

        shopsInfoService.changeShopsInfo(allShopsData.getNewShopInfo(), shops.getShopInfoId(), null);
        return shops;
    }

    public List<ShopsInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoService.getAllShopsInfo();
    }

}
