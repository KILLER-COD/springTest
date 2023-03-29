package com.mkyong.shops.dao.impl;

import com.mkyong.shops.dao.ShopInfoDAO;
import com.mkyong.shops.model.ShopInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcShopInfoDAO implements ShopInfoDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public int insert(ShopInfo shopInfo, Connection conn) {
        String sql = "INSERT INTO shop_info (shop_owner,hvhh,address_id,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, shopInfo.getShopOwner());
            preparedStatement.setInt(2, shopInfo.getHvhh());
            preparedStatement.setInt(3, shopInfo.getAddressId());
            preparedStatement.setDate(4, shopInfo.getCreateDate());
            preparedStatement.setDate(5, shopInfo.getModifyDate());

            return preparedStatement;

        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().intValue();
    }

    public ShopInfo findByShopsInfoId(int shopInfoId) {
        String sql = "SELECT * FROM shop_info WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{shopInfoId}, new BeanPropertyRowMapper<>(ShopInfo.class))
                .stream().findAny().orElse(null);
    }

    public void deleteSoft(int shopsId) {
        String sql = "UPDATE shop_info SET delete_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                new Date(System.currentTimeMillis()),
                shopsId
        );
    }

    public void deleteHard(int shopsId) {
        String sql = "DELETE FROM shop_info WHERE id = ?";
        jdbcTemplate.update(sql, shopsId);
    }

    public List<ShopInfo> getAllShopsInfo() {
        String sql = "SELECT * FROM shop_info WHERE delete_date IS NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopInfo.class));
    }

    public List<ShopInfo> getAllDeletedShopsInfo() {
        String sql = "SELECT * FROM shop_info WHERE delete_date IS NOT NULL";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopInfo.class));
    }


    public void update(ShopInfo shopInfo, int shopsInfoId, Connection conn) throws SQLException {
        String sql = "UPDATE shop_info SET shop_owner = ? ,hvhh = ? ,address_id = ? ,modify_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                shopInfo.getShopOwner(),
                shopInfo.getHvhh(),
                shopInfo.getAddressId(),
                new Date(System.currentTimeMillis()),
                shopsInfoId
        );
    }
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
//        sql = "UPDATE shop_info SET shop_owner = ? ,hvhh = ? ,address_id = ? ,modify_date = ? WHERE id = ?";
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