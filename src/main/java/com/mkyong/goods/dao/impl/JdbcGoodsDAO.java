package com.mkyong.goods.dao.impl;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.shops.model.ShopsInfo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class JdbcGoodsDAO implements GoodsDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Goods goods){

        String sql = "INSERT INTO goods (goods_name, goods_type,goods_price, product_id ,create_date,modify_date) VALUES ( ?, ?,?,?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsName());
            ps.setString(2, goods.getGoodsType());
            ps.setDouble(3, goods.getGoodsPrice());
            ps.setInt(4, goods.getProductId());
            ps.setDate(5, (Date) goods.getCreateDate());
            ps.setDate(6, (Date) goods.getModifyDate());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            closeConnection(conn);
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
                        rs.getInt("Product_id"),
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
            closeConnection(conn);
        }
    }

    public void deleteSoft(int goodsId){
        String sql = "UPDATE goods SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,new Date(System.currentTimeMillis()));
            ps.setInt(2, goodsId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int goodsId){
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

    public ArrayList<Goods> getAllGoods(){
        String sql = "SELECT * FROM goods WHERE delete_date IS NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Goods goods = null;
            ArrayList<Goods> goodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                goods = new Goods(
                        rs.getInt("id"),
                        rs.getString("goods_name"),
                        rs.getString("goods_type"),
                        rs.getDouble("goods_Price"),
                        rs.getInt("product_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                goodsList.add(goods);
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

    public ArrayList<Goods> getAllDeletedGoods(){
        String sql = "SELECT * FROM goods WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Goods goods = null;
            ArrayList<Goods> goodsList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                goods = new Goods(
                        rs.getInt("id"),
                        rs.getString("goods_name"),
                        rs.getString("goods_type"),
                        rs.getDouble("goods_Price"),
                        rs.getInt("product_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                goodsList.add(goods);
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

    public void update(String goodsName, String goodsType,double goodsPrice ,int productId,int goodsId) throws SQLException {

        Goods goods =  findByGoodsId(goodsId);

        if (goodsName != null && goodsType == null && goodsPrice == -1 &&  productId == -1){
            goods.setGoodsName(goodsName);
        } else if (goodsName == null && goodsType !=null && goodsPrice == -1 && productId == -1) {
            goods.setGoodsType(goodsType);
        } else if (goodsName == null && goodsType == null && goodsPrice > -1 && productId == -1) {
            goods.setGoodsPrice(goodsPrice);
        } else if (goodsName == null && goodsType == null && goodsPrice == -1 && productId > -1) {
            goods.setProductId(productId);
        } else {
            goods.setGoodsName(goodsName);
            goods.setGoodsType(goodsType);
            goods.setGoodsPrice(goodsPrice);
            goods.setProductId(productId);
        }
        update(goods,goodsId);
    }

    public void update(Goods goods, int shopsInfoId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE goods SET goods_name = ? ,goods_type = ? ,goods_price = ? ,product_id = ?,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, goods.getGoodsName());
        ps.setString(2,goods.getGoodsType());
        ps.setDouble(3,goods.getGoodsPrice());
        ps.setInt(4,goods.getProductId());
        ps.setDate(5, new Date(System.currentTimeMillis()));
        ps.setInt(6, shopsInfoId);
        ps.executeUpdate();
        ps.close();

        closeConnection(conn);

    }

    public void closeConnection(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }
}