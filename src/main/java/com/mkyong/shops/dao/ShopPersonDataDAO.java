package com.mkyong.shops.dao;

import com.mkyong.shops.model.ShopPersonData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ShopPersonDataDAO {
    int insert(ShopPersonData contactingInfo, Connection conn);

    Optional<ShopPersonData> findById(int contactingInfoId);

    List<ShopPersonData> findAllByIdShopId(int contactingInfoId);

    void update(ShopPersonData contactingInfo, Connection conn) throws SQLException;

    void deleteHard(int contactingInfoId) throws SQLException;

    void deleteSoft(int contactingInfoId) throws SQLException;

    List<ShopPersonData> getAllShopPersonData() throws SQLException;

    List<ShopPersonData> getAllDeletedShopPersonData() throws SQLException;

}
