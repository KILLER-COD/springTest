package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.model.ShopsInfo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class JdbcShopsInfoDAO implements ShopsInfoDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(ShopsInfo shopsInfo){

        String sql = "INSERT INTO shops_info (shop_owner,hvhh,address_id,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, shopsInfo.getShopOwner());
            ps.setInt(2, shopsInfo.getHvhh());
            ps.setInt(3, shopsInfo.getAddressId());
            ps.setDate(4, (Date) shopsInfo.getCreateDate());
            ps.setDate(5, (Date) shopsInfo.getModifyDate());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public ShopsInfo findByShopsInfoId(int shop_info_id){

        String sql = "SELECT * FROM shops_info WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, shop_info_id);
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
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
        }
    }

    public void update(String shopsOwner, int hvhh,int addressId,int shopsId) throws SQLException {

        ShopsInfo shopsInfo =  findByShopsInfoId(shopsId);

        if (shopsOwner != null && hvhh == -1 && addressId == -1){
            shopsInfo.setShopOwner(shopsOwner);
        } else if (shopsOwner == null && hvhh >-1 && addressId == -1) {
            shopsInfo.setHvhh(hvhh);
        } else if (shopsOwner != null && hvhh == -1 && addressId > -1) {
            shopsInfo.setAddressId(addressId);
        } else {
           shopsInfo.setShopOwner(shopsOwner);
           shopsInfo.setHvhh(hvhh);
           shopsInfo.setAddressId(addressId);
        }
        update(shopsInfo,shopsId);
    }

    public void update(ShopsInfo shopsInfo, int shopsInfoId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE shops SET shop_name = ? ,shop_address_id = ? ,shop_info_id = ? ,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, shopsInfo.getShopOwner());
        ps.setInt(2,shopsInfo.getHvhh());
        ps.setInt(3,shopsInfo.getAddressId());
        ps.setDate(4, new Date(System.currentTimeMillis()));
        ps.setInt(5, shopsInfoId);
        ps.executeUpdate();
        ps.close();

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }

    }
}