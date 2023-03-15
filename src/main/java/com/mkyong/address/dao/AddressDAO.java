package com.mkyong.address.dao;

import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {
    int insert(Address address, Connection conn);

    void update(Address address, int addressId, Connection conn) throws SQLException;

    //    void update(String addressName,String cityName , int addressId,Connection conn) throws SQLException;
    void deleteHard(int addressId) throws SQLException;

    void deleteSoft(int addressId) throws SQLException;

    List<Address> getAllAddress() throws SQLException;

    List<Address> getAllDeletedAddress() throws SQLException;

    Address findByAddressId(int addressId);

    List<AddressCountByCity> findCountCity();

    List<AddressCountByCity> findCountCity(Integer minCountShopInCity);
}
