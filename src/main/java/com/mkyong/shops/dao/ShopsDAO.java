package com.mkyong.shops.dao;

import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.shops.model.Shops;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopsDAO {
    void insert(Shops shops);
    Shops findByShopsId(int shopsId);
    void update(Shops shops,int shopsId) throws SQLException;
    void update(String shopsName,int shopsAddressId , int shopsInfoId,int shopsId) throws SQLException;
    void deleteHard(int shopsId) throws SQLException;
    void deleteSoft(int shopsId) throws SQLException;
    ArrayList<Shops> getAllShops() throws SQLException;
    ArrayList<Shops> getAllDeletedShops() throws SQLException;
}
