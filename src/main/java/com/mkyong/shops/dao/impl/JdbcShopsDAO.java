package com.mkyong.shops.dao.impl;

import com.mkyong.common.ColumnNames;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class JdbcShopsDAO implements ShopsDAO {
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insert(Shops shops, Connection conn) {

        String sql = "INSERT INTO shops (shop_name, shop_address_id ,shop_info_id ,create_date ,modify_date) VALUES ( ?,?,?,?,?)";
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, shops.getShopName());
            ps.setInt(2, shops.getShopAddressId());
            ps.setInt(3, shops.getShopInfoId());
            ps.setDate(4, shops.getCreateDate());
            ps.setDate(5, shops.getModifyDate());
            int shopsAdd = ps.executeUpdate();
            ps.close();
            return shopsAdd;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
//            closeConnection(conn);
        }
    }

    public Shops findByShopsId(int shops_id) {

        String sql = "SELECT * FROM shops WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, shops_id);
            Shops shops = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                shops = getResultShop(rs);
            }
            rs.close();
            ps.close();
            return shops;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void deleteSoft(int shopsId) {
        String sql = "UPDATE shops SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setInt(2, shopsId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int shopsId) {
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
            closeConnection(conn);
        }

    }

//    public void update(String shopsName, int shopsAddressId,int shopsInfoId,int shopsId,Connection conn) throws SQLException {
//
//        Shops shops =  findByShopsId(shopsId);
//
//        if (shopsName != null && shopsAddressId == -1 && shopsInfoId == -1){
//            shops.setShopName(shopsName);
//        } else if (shopsName == null && shopsAddressId >-1 && shopsInfoId == -1) {
//            shops.setShopAddressId(shopsAddressId);
//        } else if (shopsName != null && shopsAddressId == -1 && shopsInfoId > -1) {
//            shops.setShopInfoId(shopsInfoId);
//        } else {
//            shops.setShopName(shopsName);
//            shops.setShopAddressId(shopsAddressId);
//            shops.setShopInfoId(shopsInfoId);
//        }
//        update(shops,shopsId,conn);
//    }


    public void update(Shops shops, int shopsId, Connection conn) throws SQLException {

        String sql;
        PreparedStatement ps;
        if (conn == null) {
            conn = dataSource.getConnection();
        }
        sql = "UPDATE shops SET shop_name = ? ,shop_address_id = ? ,shop_info_id = ? ,modify_date = ? WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, shops.getShopName());
        ps.setInt(2, shops.getShopAddressId());
        ps.setInt(3, shops.getShopInfoId());
        ps.setDate(4, new Date(System.currentTimeMillis()));
        ps.setInt(5, shopsId);
        ps.executeUpdate();
        ps.close();

        closeConnection(conn);

    }

    public ArrayList<Shops> getAllShops() {
        String sql = "SELECT * FROM shops WHERE ISNULL(delete_date)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Shops> shopList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopList.add(getResultShop(rs));
            }
            rs.close();
            ps.close();
            return shopList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<Shops> getAllDeletedShops() {
        String sql = "SELECT * FROM shops WHERE !ISNULL(delete_date)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Shops> shopList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopList.add(getResultShop(rs));
            }
            rs.close();
            ps.close();
            return shopList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Shops getResultShop(ResultSet resultSet) throws SQLException {
        Shops shop = new Shops(
                resultSet.getInt(ColumnNames.id),
                resultSet.getString(ColumnNames.shopName),
                resultSet.getInt(ColumnNames.shopAddressId),
                resultSet.getInt(ColumnNames.shopInfoId),
                resultSet.getDate(ColumnNames.createDate),
                resultSet.getDate(ColumnNames.modifyDate),
                resultSet.getDate(ColumnNames.deleteDate)
        );
        return shop;
    }


}