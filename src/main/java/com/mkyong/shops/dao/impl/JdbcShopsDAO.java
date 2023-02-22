package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;

import javax.sql.DataSource;
import java.sql.*;

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
            ps.setString(1, shops.getShop_name());
            ps.setInt(2, shops.getShop_address_id());
            ps.setInt(3, shops.getShop_info_id());
            ps.setDate(4, (Date) shops.getCreate_date());
            ps.setDate(5, (Date) shops.getModify_date());
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
}