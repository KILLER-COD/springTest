package com.mkyong.goods.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.methods.ConsoleInputService;
import com.mkyong.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class GoodsService {
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
        return goodsDAO.findByGoodsId(id);
    }

    public void deleteGoods(int goodsId) throws SQLException {
        goodsDAO.deleteSoft(goodsId);
    }

    public int addNewGoods(Goods goods) throws InterruptedException {

        int goodsId = goodsDAO.insert(goods, null);
        return goodsId;
    }

    public void changeGoods(Goods goods, Connection conn) throws SQLException {
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
        Goods retInfo = goodsDAO.findByGoodsId(goodsId);
        return retInfo != null;
    }
}
