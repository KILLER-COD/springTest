package com.mkyong.shops.dao;

import com.mkyong.shops.model.Shops;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopsDAO {
    int insert(Shops shops, Connection conn);

    Shops findByShopsId(int shopsId);

    //  void update(Shops shops,int shopsId) throws SQLException;
    void update(Shops shops, int shopsId, Connection conn) throws SQLException;

    //    void update(String shopsName,int shopsAddressId , int shopsInfoId,int shopsId,Connection conn) throws SQLException;
    void deleteHard(int shopsId) throws SQLException;

    void deleteSoft(int shopsId) throws SQLException;

    ArrayList<Shops> getAllShops() throws SQLException;

    ArrayList<Shops> getAllDeletedShops() throws SQLException;
}
