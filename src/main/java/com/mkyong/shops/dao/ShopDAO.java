package com.mkyong.shops.dao;

import com.mkyong.shops.model.Shop;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ShopDAO {
    int insert(Shop shop, Connection conn);

    Shop findByShopsId(int shopsId);

    //  void update(Shops shops,int shopsId) throws SQLException;
    void update(Shop shop, Connection conn) throws SQLException;

    //    void update(String shopsName,int shopsAddressId , int shopsInfoId,int shopsId,Connection conn) throws SQLException;
    void deleteHard(int shopsId) throws SQLException;

    void deleteSoft(int shopsId) throws SQLException;

    List<Shop> getAllShops() throws SQLException;

    List<Shop> getAllDeletedShops() throws SQLException;
}
