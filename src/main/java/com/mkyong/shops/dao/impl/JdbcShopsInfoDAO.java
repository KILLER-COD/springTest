package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopsInfoDao;
import com.mkyong.shops.model.ShopsInfo;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcShopsInfoDAO implements ShopsInfoDao
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
            ps.setString(1, shopsInfo.getShop_owner());
            ps.setString(2, shopsInfo.getHvhh());
            ps.setInt(3, shopsInfo.getAddress_id());
            ps.setDate(4, (Date) shopsInfo.getCreate_date());
            ps.setDate(5, (Date) shopsInfo.getModify_date());
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
                        rs.getInt("Shops_info_id"),
                        rs.getString("Shop_owner"),
                        rs.getString("HVHH"),
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
}