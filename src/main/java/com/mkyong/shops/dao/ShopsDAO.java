package com.mkyong.shops.dao;

import com.mkyong.shops.model.Shops;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ShopsDAO {
    int insert(Shops shops, Connection conn);

    Shops findByShopsId(int shopsId);

    //  void update(Shops shops,int shopsId) throws SQLException;
    void update(Shops shops, Connection conn) throws SQLException;

    //    void update(String shopsName,int shopsAddressId , int shopsInfoId,int shopsId,Connection conn) throws SQLException;
    void deleteHard(int shopsId) throws SQLException;

    void deleteSoft(int shopsId) throws SQLException;

    List<Shops> getAllShops() throws SQLException;

    List<Shops> getAllDeletedShops() throws SQLException;
}
