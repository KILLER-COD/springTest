package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.ShopsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
@Component
public class JdbcShopsInfoDAO implements ShopsInfoDAO
{
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insert(ShopsInfo shopsInfo,Connection conn){

        String sql = "INSERT INTO shops_info (shop_owner,hvhh,address_id,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
       if (conn == null ){
           try {
               conn = dataSource.getConnection();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }

        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, shopsInfo.getShopOwner());
            ps.setInt(2, shopsInfo.getHvhh());
            ps.setInt(3, shopsInfo.getAddressId());
            ps.setDate(4, shopsInfo.getCreateDate());
            ps.setDate(5, shopsInfo.getModifyDate());
            ps.executeUpdate();

            ResultSet generatedKeys  = ps.getGeneratedKeys();
            int insertId;
            if (generatedKeys.next()) {
                insertId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            ps.close();

            return insertId;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
//            closeConnection(conn);
        }
    }

    public ShopsInfo findByShopsInfoId(int shopInfoId){

        String sql = "SELECT * FROM shops_info WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, shopInfoId);
            ShopsInfo shopsInfo = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                shopsInfo = new ShopsInfo(
                        rs.getInt("Id"),
                        rs.getString("Shop_owner"),
                        rs.getInt("HVHH"),
                        rs.getInt("Address_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
            }
            rs.close();
            ps.close();
            return shopsInfo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void deleteSoft(int shopsId){
        String sql = "UPDATE shops_info SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,new Date(System.currentTimeMillis()));
            ps.setInt(2, shopsId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int shopsId){
        String sql = "DELETE FROM shops_info WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, shopsId);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

    }

    public ArrayList<ShopsInfo> getAllShopsInfo(){
        String sql = "SELECT * FROM shops_info WHERE delete_date IS NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ShopsInfo shopsInfo = null;
            ArrayList<ShopsInfo> shopsInfoList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopsInfo = new ShopsInfo(
                        rs.getInt("id"),
                        rs.getString("shop_owner"),
                        rs.getInt("hvhh"),
                        rs.getInt("address_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                shopsInfoList.add(shopsInfo);
            }
            rs.close();
            ps.close();
            return shopsInfoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<ShopsInfo> getAllDeletedShopsInfo(){
        String sql = "SELECT * FROM shops_info WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ShopsInfo shopsInfo = null;
            ArrayList<ShopsInfo> shopListList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopsInfo = new ShopsInfo(
                        rs.getInt("id"),
                        rs.getString("shop_owner"),
                        rs.getInt("hvhh"),
                        rs.getInt("address_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                shopListList.add(shopsInfo);
            }
            rs.close();
            ps.close();
            return shopListList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

//    public void update(String shopsOwner, int hvhh,int addressId,int shopsInfoId,Connection conn) throws SQLException {
//
//        ShopsInfo shopsInfo =  findByShopsInfoId(shopsInfoId);
//
//        if (shopsOwner != null && hvhh == -1 && addressId == -1){
//            shopsInfo.setShopOwner(shopsOwner);
//        } else if (shopsOwner == null && hvhh >-1 && addressId == -1) {
//            shopsInfo.setHvhh(hvhh);
//        } else if (shopsOwner != null && hvhh == -1 && addressId > -1) {
//            shopsInfo.setAddressId(addressId);
//        } else {
//           shopsInfo.setShopOwner(shopsOwner);
//           shopsInfo.setHvhh(hvhh);
//           shopsInfo.setAddressId(addressId);
//        }
//        update(shopsInfo,shopsInfoId,conn);
//    }

//    public void update(ShopsInfo shopsInfo, int shopsInfoId) throws SQLException {
//
//        String sql;
//        Connection conn;
//        PreparedStatement ps;
//
//        sql = "UPDATE shops_info SET shop_owner = ? ,hvhh = ? ,address_id = ? ,modify_date = ? WHERE id = ?";
//        conn = dataSource.getConnection();
//        ps = conn.prepareStatement(sql);
//        ps.setString(1, shopsInfo.getShopOwner());
//        ps.setInt(2,shopsInfo.getHvhh());
//        ps.setInt(3,shopsInfo.getAddressId());
//        ps.setDate(4, new Date(System.currentTimeMillis()));
//        ps.setInt(5, shopsInfoId);
//        ps.executeUpdate();
//        ps.close();
//
//        closeConnection(conn);
//
//    }

    public void update(ShopsInfo shopsInfo, int shopsInfoId,Connection conn) throws SQLException {

        String sql;
        PreparedStatement ps;
        if (conn == null){
            conn = dataSource.getConnection();
        }
        sql = "UPDATE shops_info SET shop_owner = ? ,hvhh = ? ,address_id = ? ,modify_date = ? WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, shopsInfo.getShopOwner());
        ps.setInt(2,shopsInfo.getHvhh());
        ps.setInt(3,shopsInfo.getAddressId());
        ps.setDate(4, new Date(System.currentTimeMillis()));
        ps.setInt(5, shopsInfoId);
        ps.executeUpdate();
        ps.close();

//        closeConnection(conn);

    }

    public void closeConnection(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }
}