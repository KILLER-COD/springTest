package com.mkyong.shops.dao;

import com.mkyong.shops.model.ShopsInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopsInfoDAO {
    int insert(ShopsInfo shopsInfo, Connection conn);

    ShopsInfo findByShopsInfoId(int shopsInfoId);

    //    void update(ShopsInfo shopsInfo,int shopsId) throws SQLException;
    void update(ShopsInfo shopsInfo, int shopsInfoId, Connection conn) throws SQLException;

    //    void update(String shopsOwner,int hvhh , int addressId,int shopsInfoId,Connection conn) throws SQLException;
    void deleteHard(int shopsInfoId) throws SQLException;

    void deleteSoft(int shopsInfoId) throws SQLException;

    ArrayList<ShopsInfo> getAllShopsInfo() throws SQLException;

    ArrayList<ShopsInfo> getAllDeletedShopsInfo() throws SQLException;
}
