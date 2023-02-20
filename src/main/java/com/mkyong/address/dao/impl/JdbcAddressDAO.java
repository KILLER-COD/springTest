package com.mkyong.address.dao.impl;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import javax.sql.DataSource;
import java.sql.*;

public class JdbcAddressDAO implements AddressDAO
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Address address){

        String sql = "INSERT INTO address (address, city,create_date,modify_date) VALUES ( ?, ?,?,?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, address.getAddress());
            ps.setString(2, address.getCity());
            ps.setDate(3, (Date) address.getCreate_date());
            ps.setDate(4, (Date) address.getModify_date());
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

    public Address findByAddressId(int address_id){

        String sql = "SELECT * FROM address WHERE id = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, address_id);
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}