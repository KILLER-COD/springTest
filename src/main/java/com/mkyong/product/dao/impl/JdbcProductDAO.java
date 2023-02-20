package com.mkyong.product.dao.impl;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcProductDAO implements GoodsDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Goods goods){

        String sql = "INSERT INTO goods (goods_name, goods_type,goods_price,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoods_name());
            ps.setString(2, goods.getGoods_type());
            ps.setDouble(3, goods.getGoods_price());
            ps.setDate(4, (Date) goods.getCreate_date());
            ps.setDate(5, (Date) goods.getModify_date());
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

    public Goods findByGoodsId(int goods_id){

        String sql = "SELECT * FROM goods WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, goods_id);
            Goods goods = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                goods = new Goods(
                        rs.getInt("id"),
                        rs.getString("Goods_name"),
                        rs.getString("Goods_type"),
                        rs.getDouble("Goods_price"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
            }
            rs.close();
            ps.close();
            return goods;
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