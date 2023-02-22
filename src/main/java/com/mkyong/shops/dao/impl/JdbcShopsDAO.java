package com.mkyong.shops.dao.impl;

import com.mkyong.address.model.Address;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class JdbcShopsDAO implements ShopsDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Shops shops){

        String sql = "INSERT INTO shops (shops_name,shops, AGE) VALUES ( ?,?,?,? ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, shops.getShopName());
            ps.setInt(2, shops.getShopAddressId());
            ps.setInt(3, shops.getShopInfoId());
            ps.setDate(4, (Date) shops.getCreateDate());
            ps.setDate(5, (Date) shops.getModifyDate());
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

    public Shops findByShopsId(int shops_id){

        String sql = "SELECT * FROM shops WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, shops_id);
            Shops shops = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                shops = new Shops(
                        rs.getInt("Shops_id"),
                        rs.getString("Shop_name"),
                        rs.getInt("Shop_address_id"),
                        rs.getInt("Shop_info_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
            }
            rs.close();
            ps.close();
            return shops;
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
        String sql = "UPDATE shops SET delete_date = ? WHERE id = ?";
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
        String sql = "DELETE FROM shops WHERE id = ?";
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

    public void update(String shopsName, int shopsAddressId,int shopsInfoId,int shopsId) throws SQLException {

        Shops shops =  findByShopsId(shopsId);

        if (shopsName != null && shopsAddressId == -1 && shopsInfoId == -1){
            shops.setShopName(shopsName);
        } else if (shopsName == null && shopsAddressId >-1 && shopsInfoId == -1) {
            shops.setShopAddressId(shopsAddressId);
        } else if (shopsName != null && shopsAddressId == -1 && shopsInfoId > -1) {
            shops.setShopInfoId(shopsInfoId);
        } else {
            shops.setShopName(shopsName);
            shops.setShopAddressId(shopsAddressId);
            shops.setShopInfoId(shopsInfoId);
        }
        update(shops,shopsId);
    }

    public void update(Shops shops, int shopsId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE shops SET shop_name = ? ,shop_address_id = ? ,shop_info_id = ? ,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, shops.getShopName());
        ps.setInt(2,shops.getShopAddressId());
        ps.setInt(3,shops.getShopInfoId());
        ps.setDate(4, new Date(System.currentTimeMillis()));
        ps.setInt(5, shopsId);
        ps.executeUpdate();
        ps.close();

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }

    }

    public ArrayList<Shops> getAllShops(){
        String sql = "SELECT * FROM shops WHERE ISNULL(delete_date)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Shops shops = null;
            ArrayList<Shops> shopList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shops = new Shops(
                        rs.getInt("id"),
                        rs.getString("shop_owner"),
                        rs.getInt("hvhh"),
                        rs.getInt("address_ps"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                shopList.add(shops);
            }
            rs.close();
            ps.close();
            return shopList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Shops> getAllDeletedShops(){
        String sql = "SELECT * FROM shops WHERE !ISNULL(delete_date)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Shops shops = null;
            ArrayList<Shops> shopList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shops = new Shops(
                        rs.getInt("id"),
                        rs.getString("shop_name"),
                        rs.getInt("shop_address_id"),
                        rs.getInt("shop_info_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                shopList.add(shops);
            }
            rs.close();
            ps.close();
            return shopList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}