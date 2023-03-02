package com.mkyong.orders.dao.impl;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.orders.model.OrdersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
@Component
public class JdbcOrdersDAO implements OrdersDAO
{
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insert(Orders orders,Connection conn){

        String sql = "INSERT INTO orders (shop_id, create_date,modify_date) VALUES ( ?, ?, ?)";
        if (conn == null){
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orders.getShopId());
            ps.setDate(2, orders.getCreateDate());
            ps.setDate(3, orders.getModifyDate());
            ps.executeUpdate();

            ResultSet getGenerationKey = ps.getGeneratedKeys();
            int ordersId = -1;
            if (getGenerationKey.next()){
                ordersId = getGenerationKey.getInt(1);
            }
            ps.close();
            return ordersId;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
//            closeConnection(conn);
        }
    }

    public Orders findByOrdersId(int orders_id){

        String sql = "SELECT * FROM orders WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders_id);
            Orders orders = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orders = new Orders(
                        rs.getInt("id"),
                        rs.getInt("Shop_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
            }
            rs.close();
            ps.close();
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //            closeConnection(conn);
        }
    }

    public void deleteSoft(int ordersId){
        String sql = "UPDATE orders SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,new Date(System.currentTimeMillis()));
            ps.setInt(2, ordersId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int ordersId){
        String sql = "DELETE FROM orders WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ordersId);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

    }

    public ArrayList<Orders> getAllOrders(){
        String sql = "SELECT * FROM orders WHERE delete_date IS NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Orders orders = null;
            ArrayList<Orders> ordersList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders = new Orders(
                        rs.getInt("id"),
                        rs.getInt("Shop_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                ordersList.add(orders);
            }
            rs.close();
            ps.close();
            return ordersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<Orders> getAllDeletedOrders(){
        String sql = "SELECT * FROM orders WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Orders orders = null;
            ArrayList<Orders> ordersList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders = new Orders(
                        rs.getInt("id"),
                        rs.getInt("Shop_id"),
                        rs.getDate("Create_Date"),
                        rs.getDate("Modify_Date"),
                        rs.getDate("Delete_Date")
                );
                ordersList.add(orders);
            }
            rs.close();
            ps.close();
            return ordersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void getSingleOrderInfo(int ordersId) throws SQLException {
        String sql = "SELECT orders_id,shop_name,goods_name,goods_count,goods_price,o.create_date" +
                " FROM orders_goods og" +
                " join orders o on og.orders_id = o.id " +
                " join shops s on o.shop_id = s.id " +
                " join goods g on og.goods_id = g.id" +
                " WHERE og.orders_id = ? && og.delete_date IS NULL; ";

        Connection conn = dataSource.getConnection();;
        PreparedStatement ps;

        ps = conn.prepareStatement(sql);
        ps.setInt(1, ordersId);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
//        OrdersInfo o = new OrdersInfo();
        while (rs.next()){
            System.out.println(
                    "Id = " + rs.getInt("orders_id") +
                            "| Shop Name = " + rs.getString("shop_name")+
                            "| Goods Name = " + rs.getString("goods_name")+
                            "| Goods Count = " + rs.getDouble("goods_count")+
                            "| Goods Price = " + rs.getDouble("goods_price")+
                            "| Order Create = " + rs.getDate("create_date")
            );
        }
        ps.close();

        closeConnection(conn);
    }
    public void getAllOrderInfo() throws SQLException {
        String sql = "SELECT orders_id,shop_name,goods_name,goods_count,goods_price ,o.create_date" +
                        " FROM orders_goods og" +
                        " join orders o on og.orders_id = o.id " +
                        " join shops s on o.shop_id = s.id " +
                        " join goods g on og.goods_id = g.id" +
                        " WHERE og.delete_date IS NULL;";

        Connection conn = dataSource.getConnection();;
        PreparedStatement ps;

        ps = conn.prepareStatement(sql);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
//        OrdersInfo o = new OrdersInfo();
        while (rs.next()){
            System.out.println(
                    "Id = " + rs.getInt("orders_id") +
                    "| Shop Name = " + rs.getString("shop_name")+
                    "| Goods Name = " + rs.getString("goods_name")+
                    "| Goods Count = " + rs.getDouble("goods_count")+
                    "| Goods Price = " + rs.getDouble("goods_price")+
                    "| Order Create = " + rs.getDate("create_date")+
                    "\n-------------------------------------------------------"
            );
        }
        ps.close();

        closeConnection(conn);
    }
    public void getAllOrdersInfoByDate(Date date) throws SQLException {
        String sql = "SELECT orders_id,shop_name,goods_name,goods_count,goods_price,o.create_date,og.delete_date " +
                        " FROM orders_goods og" +
                        " join orders o on og.orders_id = o.id && og.create_date = o.create_date" +
                        " join shops s on o.shop_id = s.id" +
                        " join goods g on og.goods_id = g.id" +
                        " WHERE o.create_date = ? && og.delete_date IS NULL;";

        Connection conn = dataSource.getConnection();;
        PreparedStatement ps;

        ps = conn.prepareStatement(sql);
        ps.setDate(1, date);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
//        OrdersInfo o = new OrdersInfo();
        while (rs.next()){
            System.out.println(
                    "Id = " + rs.getInt("orders_id") +
                            "| Shop Name = " + rs.getString("shop_name")+
                            "| Goods Name = " + rs.getString("goods_name")+
                            "| Goods Count = " + rs.getDouble("goods_count")+
                            "| Goods Price = " + rs.getDouble("goods_price")+
                            "| Orders Create = " + rs.getDate("o.create_date")
            );
        }
        ps.close();

        closeConnection(conn);
    }

    public void update(Orders orders, int ordersId) throws SQLException {

        String sql = "UPDATE orders SET shop_id = ? ,modify_date = ? WHERE id = ?";
        Connection conn = dataSource.getConnection();;
        PreparedStatement ps;

        ps = conn.prepareStatement(sql);
        ps.setInt(1, orders.getShopId());
        ps.setDate(2, new Date(System.currentTimeMillis()));
        ps.setInt(3, ordersId);
        ps.executeUpdate();
        ps.close();

        closeConnection(conn);
    }

    public void closeConnection(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Don't close db connection");
            }
        }
    }
}