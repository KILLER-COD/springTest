package com.mkyong.methods;

import com.mkyong.goods.service.GoodsService;
import com.mkyong.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ChangeNewTransactionMethod {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProductService productService;
    public void changeGoodsMethod() throws SQLException {
        Connection conn = dataSource.getConnection();
        goodsService.changeGoods();

    }

    public void changeProduct() throws SQLException {
        Connection conn = dataSource.getConnection();
        productService.changeProduct();
    }


}
