package com.mkyong.goods.dao.impl;

import com.mkyong.common.ColumnNames;
import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class JdbcGoodsDAO implements GoodsDAO {
    @Autowired
    private DataSource dataSource;

    public int insert(Goods goods, Connection conn) {

        String sql = "INSERT INTO goods (goods_name, goods_type,goods_price, product_id ,create_date,modify_date) VALUES ( ?, ?,?,?,?,?)";
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, goods.getGoodsName());
            ps.setString(2, goods.getGoodsType());
            ps.setDouble(3, goods.getGoodsPrice());
            ps.setInt(4, goods.getProductId());
            ps.setDate(5, (Date) goods.getCreateDate());
            ps.setDate(6, (Date) goods.getModifyDate());
            ps.executeUpdate();
            int goodsId = -1;
            ResultSet getGeneratorKey = ps.getGeneratedKeys();
            if (getGeneratorKey.next()) {
                goodsId = getGeneratorKey.getInt(1);
            }
            ps.close();
            return goodsId;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
//            closeConnection(conn);
        }
    }

    public Goods findByGoodsId(int goods_id) {

        String sql = "SELECT * FROM goods WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, goods_id);
            Goods goods = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                goods = getResultGoods(rs);
            }
            rs.close();
            ps.close();
            return goods;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void deleteSoft(int goodsId) {
        String sql = "UPDATE goods SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setInt(2, goodsId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int goodsId) {
        String sql = "DELETE FROM goods WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, goodsId);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

    }

    public ArrayList<Goods> getAllGoods() {
        String sql = "SELECT * FROM goods WHERE delete_date IS NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Goods goods = null;
            ArrayList<Goods> goodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                goodsList.add(getResultGoods(rs));
            }
            rs.close();
            ps.close();
            return goodsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<Goods> getAllDeletedGoods() {
        String sql = "SELECT * FROM goods WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Goods> goodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                goodsList.add(getResultGoods(rs));
            }
            rs.close();
            ps.close();
            return goodsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

//    public void update(String goodsName, String goodsType, double goodsPrice, int productId, int goodsId,Connection conn) throws SQLException {
//
//        Goods goods = findByGoodsId(goodsId);
//
//        if (goodsName != null) {
//            goods.setGoodsName(goodsName);
//        }
//        if (goodsType != null) {
//            goods.setGoodsType(goodsType);
//        }
//        if (goodsPrice > -1) {
//            goods.setGoodsPrice(goodsPrice);
//        }
//        if (productId > -1) {
//            goods.setProductId(productId);
//        }
//        update(goods, goodsId,conn);
//    }

    public void update(Goods goods, int goodsId, Connection conn) throws SQLException {

        String sql = "UPDATE goods SET goods_name = ? ,goods_type = ? ,goods_price = ? ,product_id = ?,modify_date = ? WHERE id = ?";
        PreparedStatement ps;

        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        ps = conn.prepareStatement(sql);
        ps.setString(1, goods.getGoodsName());
        ps.setString(2, goods.getGoodsType());
        ps.setDouble(3, goods.getGoodsPrice());
        ps.setInt(4, goods.getProductId());
        ps.setDate(5, new Date(System.currentTimeMillis()));
        ps.setInt(6, goodsId);
        ps.executeUpdate();
        ps.close();

//        closeConnection(conn);

    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public Goods getResultGoods(ResultSet resultSet) throws SQLException {
        Goods goods = new Goods(
                resultSet.getInt(ColumnNames.id),
                resultSet.getString(ColumnNames.goodsName),
                resultSet.getString(ColumnNames.goodsType),
                resultSet.getDouble(ColumnNames.goodsPrice),
                resultSet.getInt(ColumnNames.productId),
                resultSet.getDate(ColumnNames.createDate),
                resultSet.getDate(ColumnNames.modifyDate),
                resultSet.getDate(ColumnNames.deleteDate)
        );
        return goods;
    }

    public int plus(int n1, int n2) {
        if (n1 > 100) {
            return 10;
        }
        return n1 + n2;
    }
}