package com.mkyong.address.dao.impl;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class JdbcAddressDAO implements AddressDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ArrayList<AddressCountByCity> findCountCity(){
        String sql = "SELECT COUNT(id) cnt, city FROM address WHERE delete_date IS NULL GROUP BY city;";
        Connection conn =null;
        ArrayList<AddressCountByCity> addressCountByCityList = new ArrayList<>();
        AddressCountByCity addressCountByCity;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 addressCountByCity = new AddressCountByCity(
                         rs.getInt("cnt"),
                         rs.getString("city")
                 );
                 addressCountByCityList.add(addressCountByCity);
            }
            ps.close();
            rs.close();

            return addressCountByCityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<AddressCountByCity> findCountCity(Integer minCountShopInCity){

        String sql = "SELECT COUNT(id) cnt,BINARY city FROM address WHERE delete_date IS NULL GROUP BY BINARY city HAVING COUNT(id) > ?;";
        Connection conn =null;
        ArrayList<AddressCountByCity> addressCountByCityList = new ArrayList<>();
        AddressCountByCity addressCountByCity;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, minCountShopInCity);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                addressCountByCity = new AddressCountByCity(
                        rs.getInt("cnt"),
                        rs.getString("BINARY city")
                );
                addressCountByCityList.add(addressCountByCity);
            }
            ps.close();
            rs.close();

            return addressCountByCityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public void deleteSoft(int addressId){
        String sql = "UPDATE address SET delete_date = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1,new Date(System.currentTimeMillis()));
            ps.setInt(2, addressId);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void deleteHard(int addressId){
        String sql = "DELETE FROM address WHERE id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, addressId);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }


    }

    public void insert(Address address){

        String sql = "INSERT INTO address (address, city,create_date,modify_date) VALUES ( ?, ?,?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, address.getAddress());
            ps.setString(2, address.getCity());
            ps.setDate(3, (Date) address.getCreateDate());
            ps.setDate(4, (Date) address.getModifyDate());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            closeConnection(conn);
        }
    }

    public void update(String addressName, String cityName,int addressId) throws SQLException {

        Address address =  findByAddressId(addressId);

        if (addressName != null && cityName == null){
            address.setAddress(addressName);
        } else if (cityName != null && addressName == null) {
            address.setCity(cityName);
        } else {
            address.setAddress(addressName);
            address.setCity(cityName);
        }
        update(address,addressId);
    }

    public void update(Address address, int addressId) throws SQLException {

        String sql;
        Connection conn;
        PreparedStatement ps;

        sql = "UPDATE address SET address = ? ,city = ? ,modify_date = ? WHERE id = ?";
        conn = dataSource.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, address.getAddress());
        ps.setString(2,address.getCity());
        ps.setDate(3, new Date(System.currentTimeMillis()));
        ps.setInt(4, addressId);
        ps.executeUpdate();
        ps.close();

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            } finally {
                closeConnection(conn);
            }
        }

    }

    public ArrayList<Address> getAllAddress(){
        String sql = "SELECT * FROM address WHERE delete_date IS NULL ";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Address address = null;
            ArrayList<Address> addressList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    address = new Address(
                            rs.getInt("id"),
                            rs.getString("Address"),
                            rs.getString("City"),
                            rs.getDate("Create_Date"),
                            rs.getDate("Modify_Date"),
                            rs.getDate("Delete_Date")
                    );
                    addressList.add(address);
            }
            rs.close();
            ps.close();
            return addressList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList<Address> getAllDeletedAddress(){
        String sql = "SELECT * FROM address WHERE delete_date IS NOT NULL";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Address address = null;
            ArrayList<Address> addressList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    address = new Address(
                            rs.getInt("id"),
                            rs.getString("Address"),
                            rs.getString("City"),
                            rs.getDate("Create_Date"),
                            rs.getDate("Modify_Date"),
                            rs.getDate("Delete_Date")
                    );
                    addressList.add(address);
            }
            rs.close();
            ps.close();
            return addressList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    public Address findByAddressId(int addressId){

        String sql = "SELECT * FROM address WHERE  id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, addressId);
            Address address = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                    address = new Address(
                            rs.getInt("id"),
                            rs.getString("Address"),
                            rs.getString("City"),
                            rs.getDate("Create_Date"),
                            rs.getDate("Modify_Date"),
                            rs.getDate("Delete_Date")
                    );
            }
            rs.close();
            ps.close();
            return address;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
           closeConnection(conn);
        }
    }

    public void closeConnection(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }
}

