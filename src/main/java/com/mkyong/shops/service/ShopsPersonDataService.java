package com.mkyong.shops.service;

import com.mkyong.shops.dao.ShopPersonDataDAO;
import com.mkyong.shops.model.ShopPersonData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShopsPersonDataService {
    private final ShopPersonDataDAO personDataDAO;

    public ShopPersonData findById(int contactingInfoId) {
        return personDataDAO.findById(contactingInfoId).orElseThrow();
    }

    public List<ShopPersonData> findAllByIdShopId(int shopId) {
        return personDataDAO.findAllByIdShopId(shopId);
    }

    public int create(ShopPersonData newShopPersonData, Connection conn) {
//        ShopPersonData shopPersonData = ShopPersonData.builder()
//                .personName(newShopPersonData.getPersonName())
//                .personEmail(newShopPersonData.getPersonEmail())
//                .shopId(newShopPersonData.getShopId())
//                .build();
        return personDataDAO.insert(newShopPersonData, null);
    }

    public void changeShopPersonData(ShopPersonData personData, Connection conn) throws SQLException {
        personDataDAO.update(personData, conn);
    }

    public List<ShopPersonData> getAllShopPersonData() throws SQLException {
        return personDataDAO.getAllShopPersonData();
    }

    public void deleteSoft(int shopPersonDataId) throws SQLException {
        personDataDAO.deleteSoft(shopPersonDataId);
    }

    public void deleteHard(int shopPersonDataId) throws SQLException {
        personDataDAO.deleteHard(shopPersonDataId);
    }

}
