package com.mkyong.shops.dao;

import com.mkyong.shops.model.ShopPersonContact;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ShopPersonContactDAO {
    int insert(ShopPersonContact personContact, Connection conn);

    Optional<ShopPersonContact> findById(int contactingInfoId);

    void update(ShopPersonContact personContact, Connection conn) throws SQLException;

    void deleteHard(int personContactId) throws SQLException;

    void deleteSoft(int personContactId) throws SQLException;

    void deleteSoftByShopPersonDataId(int personDataId) throws SQLException;

    List<ShopPersonContact> getAllShopPersonContact() throws SQLException;

    List<ShopPersonContact> findByShopPersonDataId(int shopPersonDataId) throws SQLException;

    List<ShopPersonContact> getAllDeletedShopPersonContacts() throws SQLException;

}
