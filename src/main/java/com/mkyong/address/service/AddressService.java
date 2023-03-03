package com.mkyong.address.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.methods.ConsoleInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class AddressService {
    @Autowired
    private ConsoleInputService consoleInputService;
    @Autowired
    private AddressDAO addressDAO;

    public ArrayList<Address> getAllAddress() throws SQLException {
        return addressDAO.getAllAddress();
    }

    public ArrayList<Address> getAllDeletedAddress() throws SQLException {
        return addressDAO.getAllDeletedAddress();
    }

    public void deleteAddress(int addressId) throws SQLException {
        System.out.println("Set Delete Address type | 1 - hard | 2 - Soft");
        int deleteType = consoleInputService.readInt();
        if (deleteType == 1) {
            addressDAO.deleteHard(addressId);
        } else if (deleteType == 2) {
            addressDAO.deleteSoft(addressId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteAddress(addressId);
        }
    }

    public int addNewAddress(Connection conn) {

        System.out.println("Set shop Address");
        String address_name = consoleInputService.readString();
        System.out.println("Set Name City where in shop");
        String address_city = consoleInputService.readString();

        Address address = new Address();
        address.setAddress(address_name);
        address.setCity(address_city);
        address.setCreateDate(new Date(System.currentTimeMillis()));
        address.setModifyDate(new Date(System.currentTimeMillis()));

        System.out.println(address);
        return addressDAO.insert(address, conn);
    }

    public void countShopCity() {
        System.out.println("Set minimum count shop in city ( ? > 0 )| default=0");
        int minCountShop = consoleInputService.readInt();

        ArrayList<AddressCountByCity> addressCountByCityList;
        if (minCountShop < 0) {
            addressCountByCityList = addressDAO.findCountCity();
        } else {
            addressCountByCityList = addressDAO.findCountCity(minCountShop);
        }
        for (AddressCountByCity a : addressCountByCityList) {
            System.out.println("count = " + a.getCount() + " | " + "city = " + a.getCity());
        }
    }

    public void changeAddress() throws SQLException {
        addressPrint();
        System.out.println("Set Address  ID ");
        int addressId = consoleInputService.readInt();

        Address address = addressDAO.findByAddressId(addressId);
        System.out.println(address.toString());

        System.out.println("Change shop Address");
        String addressName = consoleInputService.readString();
        while (addressName.equals("")) {
            System.out.println("Change shop Address");
            addressName = consoleInputService.readString();
        }

        System.out.println("Change Name City where in shop");
        String addressCity = consoleInputService.readString();
        ;
        while (addressCity.equals("")) {
            System.out.println("Change Name City where in shop");
            addressCity = consoleInputService.readString();
            ;
        }

        address.setAddress(addressName);
        address.setCity(addressCity);

        addressDAO.update(address, addressId, null);

    }

    public void addressPrint() {
        try {
            addressDAO.getAllAddress().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int addressId) {
        Address retInfo = addressDAO.findByAddressId(addressId);
        return retInfo != null;
    }


}
