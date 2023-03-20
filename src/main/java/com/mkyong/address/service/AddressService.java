package com.mkyong.address.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.methods.ConsoleInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class AddressService {
    @Autowired
    private ConsoleInputService consoleInputService;
    @Autowired
    private AddressDAO addressDAO;

    public List<Address> getAllAddress() throws SQLException {
        return addressDAO.getAllAddress();
    }

    public Address findByAddressId(int id) throws SQLException {
        return addressDAO.findByAddressId(id);
    }

    public List<Address> getAllDeletedAddress() throws SQLException {
        return addressDAO.getAllDeletedAddress();
    }

    public void deleteAddress(int addressId) throws SQLException {
        addressDAO.deleteSoft(addressId);
    }

    public int addNewAddress(Address address) {
        address.setCreateDate(new Date(System.currentTimeMillis()));
        address.setModifyDate(new Date(System.currentTimeMillis()));
        System.out.println(address);
        return addressDAO.insert(address);
    }

    public void countShopCity() {
        System.out.println("Set minimum count shop in city ( ? > 0 )| default=0");
        int minCountShop = consoleInputService.readInt();

        List<AddressCountByCity> addressCountByCityList;
        if (minCountShop < 0) {
            addressCountByCityList = addressDAO.findCountCity();
        } else {
            addressCountByCityList = addressDAO.findCountCity(minCountShop);
        }
        for (AddressCountByCity a : addressCountByCityList) {
            System.out.println("count = " + a.getCount() + " | " + "city = " + a.getCity());
        }
    }

    public void changeAddress(Address address, int addressId) throws SQLException {
        addressDAO.update(address, addressId, null);
    }

    public boolean existsById(int addressId) {
        Address retInfo = addressDAO.findByAddressId(addressId);
        return retInfo != null;
    }

    public Address checkedUpdateAddress(Address newaddress, int addressId) throws SQLException {
        //Checked Changes Shops Address -------------------------------------------------
        Address address = findByAddressId(addressId);

        if (!address.getAddress().equals(newaddress.getAddress())) {
            address.setAddress(newaddress.getAddress());
        }
        if (!address.getCity().equals(newaddress.getCity())) {
            address.setCity(newaddress.getCity());
        }

        return address;
    }

}
