package com.mkyong.shops.dao;

import com.mkyong.shops.model.Shops;
import com.mkyong.shops.model.ShopsInfo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopsInfoDAO {
    void insert(ShopsInfo shopsInfo);
    ShopsInfo findByShopsInfoId(int shopsInfoId);
    void update(ShopsInfo shopsInfo,int shopsId) throws SQLException;
    void update(String shopsName,int shopsAddressId , int shopsInfoId,int shopsId) throws SQLException;
    void deleteHard(int shopsInfoId) throws SQLException;
    void deleteSoft(int shopsInfoId) throws SQLException;
    ArrayList<ShopsInfo> getAllShopsInfo() throws SQLException;
    ArrayList<ShopsInfo> getAllDeletedShopsInfo() throws SQLException;
}
