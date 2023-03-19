package com.mkyong.shops.service;

import com.mkyong.address.service.AddressService;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.model.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Component
public class ShopsService {
    @Autowired
    private ConsoleInputService consoleInputService;
    @Autowired
    private ShopsDAO shopsDAO;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ShopsInfoService shopsInfoService;

    public List<Shops> getAllShops() throws SQLException {
        return shopsDAO.getAllShops();
    }

    public List<Shops> getAllDeletedShops() throws SQLException {
        return shopsDAO.getAllDeletedShops();
    }

    public Shops findByShopsId(int id) throws SQLException {
        return shopsDAO.findByShopsId(id);
    }

    public void deleteShops(int shopsId) throws SQLException {
        shopsDAO.deleteSoft(shopsId);
    }

    public int addNewShops(Shops shops, Connection conn) {
        shops.setCreateDate(new Date(System.currentTimeMillis()));
        shops.setModifyDate(new Date(System.currentTimeMillis()));
        return shopsDAO.insert(shops, conn);

    }

    public void changeShops(Shops shops, Connection conn) throws SQLException {
        shopsDAO.update(shops, conn);

    }

    public void shopsPrint() {
        try {
            shopsDAO.getAllShops().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int shopsId) {
        Shops retInfo = shopsDAO.findByShopsId(shopsId);
        return retInfo != null;
    }


}
