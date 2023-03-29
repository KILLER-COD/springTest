package com.mkyong.shops.dao;

import com.mkyong.shops.model.ShopInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ShopInfoDAO {
    int insert(ShopInfo shopInfo, Connection conn);

    ShopInfo findByShopsInfoId(int shopsInfoId);

    void update(ShopInfo shopInfo, int shopsInfoId, Connection conn) throws SQLException;

    //    void update(String shopsOwner,int hvhh , int addressId,int shopsInfoId,Connection conn) throws SQLException;
    void deleteHard(int shopInfoId) throws SQLException;

    void deleteSoft(int shopInfoId) throws SQLException;

    List<ShopInfo> getAllShopsInfo() throws SQLException;

    List<ShopInfo> getAllDeletedShopsInfo() throws SQLException;
}
