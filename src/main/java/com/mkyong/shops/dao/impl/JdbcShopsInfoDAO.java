package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.ShopsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcShopsInfoDAO implements ShopsInfoDAO {
    private final JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public JdbcShopsInfoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insert(ShopsInfo shopsInfo, Connection conn) {
        String sql = "INSERT INTO shops_info (shop_owner,hvhh,address_id,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        return jdbcTemplate.update(sql,
                shopsInfo.getShopOwner(),
                shopsInfo.getHvhh(),
                shopsInfo.getAddressId(),
                shopsInfo.getCreateDate(),
                shopsInfo.getModifyDate()
        );
    }

    public ShopsInfo findByShopsInfoId(int shopInfoId) {
        String sql = "SELECT * FROM shops_info WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{shopInfoId}, new BeanPropertyRowMapper<>(ShopsInfo.class))
                .stream().findAny().orElse(null);
    }

    public void deleteSoft(int shopsId) {
        String sql = "UPDATE shops_info SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                shopsId
        );
    }

    public void deleteHard(int shopsId) {
        String sql = "DELETE FROM shops_info WHERE id = ?";
        jdbcTemplate.update(sql, shopsId);
    }

    public List<ShopsInfo> getAllShopsInfo() {
        String sql = "SELECT * FROM shops_info WHERE delete_date IS NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopsInfo.class));
    }

    public List<ShopsInfo> getAllDeletedShopsInfo() {
        String sql = "SELECT * FROM shops_info WHERE delete_date IS NOT NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopsInfo.class));
    }


    public void update(ShopsInfo shopsInfo, int shopsInfoId, Connection conn) throws SQLException {
        String sql = "UPDATE shops_info SET shop_owner = ? ,hvhh = ? ,address_id = ? ,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                shopsInfo.getShopOwner(),
                shopsInfo.getHvhh(),
                shopsInfo.getAddressId(),
                new Date(System.currentTimeMillis()),
                shopsInfoId
        );
    }

//    public void closeConnection(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//            }
//        }
//    }

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

}