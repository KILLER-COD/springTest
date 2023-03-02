package com.mkyong.methods;

import com.mkyong.address.service.AddressService;
import com.mkyong.goods.service.GoodsService;
import com.mkyong.orders.service.OrdersGoodsService;
import com.mkyong.orders.service.OrdersService;
import com.mkyong.product.service.ProductService;
import com.mkyong.shops.service.ShopsInfoService;
import com.mkyong.shops.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class AddNewTransactionMethod {
    @Autowired
    private DataSource dataSource;
    private ApplicationContext context;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ShopsService shopsService;
    @Autowired
    private ShopsInfoService shopsInfoService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersGoodsService ordersGoodsService;
    @Autowired
    private ProductService productService;

    public AddNewTransactionMethod(ApplicationContext context) {
        this.context = context;
    }

    public void addNewOrdersMethod() throws SQLException {

        Connection conn = dataSource.getConnection();

        try {
            conn.setAutoCommit(false);
            int ordersAddId = ordersService.addNewOrders(conn);
            ArrayList<Integer> ordersGoodsIdList = ordersGoodsService.addNewOrdersGoods(ordersAddId, conn);
            if (ordersAddId > 0 && ordersGoodsIdList.size() > 0) {
                conn.commit();
                conn.close();
            } else {
                conn.rollback();
                conn.close();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void addNewGoodsMethod() throws SQLException {

        Connection conn = dataSource.getConnection();

        try {
            conn.setAutoCommit(false);
            int goodsAddOk = goodsService.addNewGoods(conn);
            if (goodsAddOk > 0) {
                conn.commit();
                conn.close();
            } else {
                conn.rollback();
                conn.close();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void addNewShopMethod() throws SQLException {

        Connection conn = dataSource.getConnection();

        try {
            conn.setAutoCommit(false);
            int addressIdShop = addressService.addNewAddress(conn);
            System.out.println(addressIdShop);
            int addressIdShopInfo = addressService.addNewAddress(conn);
            System.out.println(addressIdShopInfo);
            int shopInfoId = shopsInfoService.addNewShopsInfo(conn, addressIdShopInfo);
            System.out.println(shopInfoId);
            int shopsAdd = shopsService.addNewShops(conn, addressIdShop, shopInfoId);
            System.out.println(shopsAdd);
            if (shopsAdd > 0) {
                System.out.println("Commit (Ok) Add DB");
                conn.commit();
                conn.close();
            } else {
                System.out.println("Commit (Error) Not Add Db");
                conn.rollback();
                conn.close();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void addNewProductMethod() {
        productService.addNewProduct(null);
    }

    public void addNewAddressMethod() {
        addressService.addNewAddress(null);
    }


}
