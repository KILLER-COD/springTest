package com.mkyong.address.dao;

import com.mkyong.address.model.Address;

public interface AddressDAO {
    public void insert(Address address);
    public Address findByAddressId(int address_id);
}
