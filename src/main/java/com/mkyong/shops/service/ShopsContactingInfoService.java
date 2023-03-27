package com.mkyong.shops.service;

import com.mkyong.shops.dao.ShopContactingInfoDAO;
import com.mkyong.shops.model.ShopContactingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShopsContactingInfoService {
    private final ShopContactingInfoDAO shopContactingInfoDAO;

    public ShopContactingInfo findById(int contactingInfoId) {
        return shopContactingInfoDAO.findById(contactingInfoId).orElseThrow();
    }

    public int create(ShopContactingInfo newShopContactingInfo, Connection conn) {
        ShopContactingInfo shopContactingInfo = ShopContactingInfo.builder()
                .name(newShopContactingInfo.getName())
                .email(newShopContactingInfo.getEmail())
                .phone1(newShopContactingInfo.getPhone1())
                .phone2(newShopContactingInfo.getPhone2())
                .shopInfoId(newShopContactingInfo.getShopInfoId())
                .build();
        return shopContactingInfoDAO.insert(shopContactingInfo, null);

    }

    public List<ShopContactingInfo> getAllShopContactingInfo() throws SQLException {
        return shopContactingInfoDAO.getAllShopContactingInfo();
    }

    public void deleteSoft(int contactingInfoId) throws SQLException {
        shopContactingInfoDAO.deleteSoft(contactingInfoId);
    }

    public void deleteHard(int contactingInfoId) throws SQLException {
        shopContactingInfoDAO.deleteHard(contactingInfoId);
    }

}
