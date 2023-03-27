package com.mkyong.shops.dao;

import com.mkyong.shops.model.ShopContactingInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ShopContactingInfoDAO {
    int insert(ShopContactingInfo contactingInfo, Connection conn);

    Optional<ShopContactingInfo> findById(int contactingInfoId);

    void update(ShopContactingInfo contactingInfo, Connection conn) throws SQLException;

    void deleteHard(int contactingInfoId) throws SQLException;

    void deleteSoft(int contactingInfoId) throws SQLException;

    List<ShopContactingInfo> getAllShopContactingInfo() throws SQLException;

    List<ShopContactingInfo> getAllDeletedShops() throws SQLException;

}
