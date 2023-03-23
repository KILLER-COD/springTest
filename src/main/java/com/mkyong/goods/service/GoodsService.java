package com.mkyong.goods.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.GetAllGoodsData;
import com.mkyong.goods.model.Goods;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.product.model.Product;
import com.mkyong.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class GoodsService {
    @Autowired
    private JoinByQueryDAO joinByQueryDAO;
    @Autowired
    private ProductService productService;
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private ConsoleInputService consoleInputService;

    public List<Goods> getAllGoods() throws SQLException {
        return goodsDAO.getAllGoods();
    }

    public List<Goods> getAllDeletedGoods() throws SQLException {
        return goodsDAO.getAllDeletedGoods();
    }

    public Goods findByGoodsId(int id) throws SQLException {
        return goodsDAO.findByGoodsId(id).orElseThrow();
    }

    public void deleteGoods(int goodsId) throws SQLException {
        goodsDAO.deleteSoft(goodsId);
    }

    public int addNewGoods(GetAllGoodsData getAllGoodsData) throws InterruptedException {


        Product product = Product.builder()
                .productType(getAllGoodsData.getProductType())
                .productName(getAllGoodsData.getProductName())
                .build();
        int productId = productService.addNewProduct(product, null);
        Goods goods = Goods.builder()
                .goodsName(getAllGoodsData.getGoodsName())
                .goodsType(getAllGoodsData.getGoodsType())
                .goodsPrice(getAllGoodsData.getGoodsPrice())
                .productId(productId)
                .build();
        return goodsDAO.insert(goods, null);
    }

    public void changeGoods(GetAllGoodsData getAllGoodsData, int goodsId, Connection conn) throws SQLException {
        Goods goods = Goods.builder()
                .goodsName(getAllGoodsData.getGoodsName())
                .goodsType(getAllGoodsData.getGoodsType())
                .goodsPrice(getAllGoodsData.getGoodsPrice())
                .build();
        goodsDAO.update(goods, conn);
    }

    public void goodsPrint() {
        try {
            getAllGoods().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int goodsId) {
        return goodsDAO.findByGoodsId(goodsId).isPresent();
    }

    public List<GetAllGoodsData> getAllGoodsData() {
        return joinByQueryDAO.getAllGoodsData();
    }

    public GetAllGoodsData getSingleGoodsData(int goodsId) {
        return joinByQueryDAO.getSingleGoodsData(goodsId);
    }
}
