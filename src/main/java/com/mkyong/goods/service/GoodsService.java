package com.mkyong.goods.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.goods.model.GoodsAllData;
import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.product.model.Product;
import com.mkyong.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GoodsService {

    private final JoinByQueryDAO joinByQueryDAO;

    private final ProductService productService;

    private final GoodsDAO goodsDAO;

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

    public int addNewGoods(GoodsAllData goodsAllData) throws InterruptedException {
        Goods goods = Goods.builder()
                .goodsName(goodsAllData.getGoodsName())
                .goodsType(goodsAllData.getGoodsType())
                .goodsPrice(goodsAllData.getGoodsPrice())
                .productId(goodsAllData.getProductId())
                .build();
        return goodsDAO.insert(goods, null);
    }

    public void changeGoods(GoodsAllData goodsAllData, Connection conn) throws SQLException {
        Goods goods = Goods.builder()
                .id(goodsAllData.getId())
                .goodsName(goodsAllData.getGoodsName())
                .goodsType(goodsAllData.getGoodsType())
                .goodsPrice(goodsAllData.getGoodsPrice())
                .productId(goodsAllData.getProductId())
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

    public List<GoodsAllData> getAllGoodsData() {
        return joinByQueryDAO.getAllGoodsData();
    }

    public List<Product> getAllProduct() throws SQLException {
        return productService.getAllProduct();
    }

    public GoodsAllData getSingleGoodsData(int goodsId) {
        return joinByQueryDAO.getSingleGoodsData(goodsId);
    }
}
