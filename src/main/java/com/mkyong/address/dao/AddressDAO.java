package com.mkyong.address.dao;

import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddressDAO {
    void insert(Address address);
    void update(Address address,int addressId) throws SQLException;
    void update(String addressName,String cityName , int addressId) throws SQLException;
    void delete(int addressId) throws SQLException;
    ArrayList<Address> getAllAddress() throws SQLException;
    Address findByAddressId(int addressId);
    ArrayList<AddressCountByCity> findCountCity();
}
