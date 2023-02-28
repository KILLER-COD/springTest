package com.mkyong.address.service;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class AddressService {
   Scanner scanner = new Scanner(System.in);
   Scanner scanner1 = new Scanner(System.in);
   Scanner scanner2 = new Scanner(System.in);

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
        int deleteType = scanner.nextInt();
        if (deleteType == 1){
            addressDAO.deleteHard(addressId);
        } else if (deleteType == 2) {
            addressDAO.deleteSoft(addressId);
        } else {
            System.out.println("Error : Set correct Number ");
            deleteAddress(addressId);
        }
    }

    public int addNewAddress(Connection conn)  {

            System.out.println("Set shop Address");
            String address_name  = scanner1.next();
            System.out.println("Set Name City where in shop");
            String address_city  = scanner1.next();

            Address address = new Address();
            address.setAddress(address_name);
            address.setCity(address_city);
            address.setCreateDate(new Date(System.currentTimeMillis()));
            address.setModifyDate(new Date(System.currentTimeMillis()));

        System.out.println(address);
        return addressDAO.insert(address,conn);
    }
    public void countShopCity() {
        System.out.println("Set minimum count shop in city ( ? > 0 )| default=0");
        Integer minCountShop  = scanner1.nextInt();

        ArrayList<AddressCountByCity> addressCountByCityList;
        if(minCountShop < 0 ){
            addressCountByCityList = addressDAO.findCountCity();
            for (AddressCountByCity a : addressCountByCityList) {
                System.out.println("count = " + a.getCount() +" | " + "city = " + a.getCity() );
            }
        } else {
            addressCountByCityList = addressDAO.findCountCity(minCountShop);
            for (AddressCountByCity a : addressCountByCityList) {
                System.out.println("count = " + a.getCount() +" | " + "city = " + a.getCity() );
            }
        }
        scanner1.close();
    }

    public void changeAddress( Connection conn) throws SQLException {
        String addressName;
        String addressCity;

        System.out.println("Set Address  ID ");
        int addressId = scanner2.nextInt();

        Address address =  addressDAO.findByAddressId(addressId);
        System.out.println(address.toString());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Change shop Address");
        addressName  = scanner.next();
        while (addressName.equals("")){
            System.out.println("Change shop Address");
            addressName  = scanner.nextLine();
        }

        System.out.println("Change Name City where in shop");
        addressCity  = scanner.next();
        while (addressCity.equals("")){
            System.out.println("Change Name City where in shop");
            addressCity  = scanner.nextLine();
        }

        address.setAddress(addressName);
        address.setCity(addressCity);

        addressDAO.update(address,addressId,null);


    }


}
