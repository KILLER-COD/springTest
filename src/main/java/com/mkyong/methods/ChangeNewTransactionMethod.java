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

@Component
public class ChangeNewTransactionMethod {
    private ApplicationContext context;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ShopsService shopsService;
    @Autowired
    private ShopsInfoService shopsInfoService;
    @Autowired
    private OrdersGoodsService ordersGoodsService;

    public ChangeNewTransactionMethod(ApplicationContext context) {
        this.context = context;
    }

//    public void changeGoodsMethod() throws SQLException {
//        Connection conn = dataSource.getConnection();
//        conn.setAutoCommit(false);
//        goodsService.changeGoods(conn);
//        conn.commit();
//        conn.close();
//    }

//    public void changeProductMethod() throws SQLException {
//        productService.changeProduct();
//    }

//    public void changeAddressMethod() throws SQLException {
//        addressService.changeAddress();
//    }

    public void changeOrdersMethod() throws SQLException {
        ordersService.changeOrders();
    }

    public void changeOrdersGoodsMethod() throws SQLException {
        ordersGoodsService.changeOrdersGoods();
    }

    public void changeShopsMethod() throws SQLException {
        Connection conn = dataSource.getConnection();
        shopsService.changeShops(conn);
        conn.close();
    }

//    public void changeShopsInfoMethod() throws SQLException {
//        Connection conn = dataSource.getConnection();
//        shopsInfoService.changeShopsInfo(conn);
//        conn.close();
//    }


}
