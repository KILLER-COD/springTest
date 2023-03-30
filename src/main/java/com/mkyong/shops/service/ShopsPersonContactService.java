package com.mkyong.shops.service;

import com.mkyong.shops.dao.ShopPersonContactDAO;
import com.mkyong.shops.model.ShopPersonContact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShopsPersonContactService {
    private final ShopPersonContactDAO personContactDAO;

    public ShopPersonContact findById(int contactingInfoId) {
        return personContactDAO.findById(contactingInfoId).orElseThrow();
    }

    public int create(ShopPersonContact newShopPersonContact, Connection conn) {
//        ShopPersonContact shopPersonContact = ShopPersonContact.builder()
//                .phone(newShopPersonContact.getPhone())
//                .shopPersonDataId(newShopPersonContact.getShopPersonDataId())
//                .build();
        return personContactDAO.insert(newShopPersonContact, null);
    }

    public List<ShopPersonContact> getAllShopContactingInfo() throws SQLException {
        return personContactDAO.getAllShopPersonContact();
    }

    public List<ShopPersonContact> findByShopPersonDataId(int shopPersonDataId) throws SQLException {
        return personContactDAO.findByShopPersonDataId(shopPersonDataId);
    }

    public void deleteSoft(int personContactId) throws SQLException {
        personContactDAO.deleteSoft(personContactId);
    }

    public void deleteSoftByShopPersonDataId(int personDataId) throws SQLException {
        personContactDAO.deleteSoftByShopPersonDataId(personDataId);
    }

    public void deleteHard(int contactingInfoId) throws SQLException {
        personContactDAO.deleteHard(contactingInfoId);
    }


    public void changeShopPersonContact(ShopPersonContact personContact, Connection conn) throws SQLException {
        personContactDAO.update(personContact, conn);
    }
}
