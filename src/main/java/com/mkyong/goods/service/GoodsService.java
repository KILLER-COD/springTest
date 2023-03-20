package com.mkyong.goods.service;

import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.goods.model.ShowGoods;
import com.mkyong.methods.ConsoleInputService;
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

    public int addNewGoods(ShowGoods showGoods) throws InterruptedException {
        Goods goods = showGoods.getGoods();
        Product product = showGoods.getProduct();
        int productId = productService.addNewProduct(product, null);
        goods.setProductId(productId);
        int goodsId = goodsDAO.insert(goods, null);
        return goodsId;
    }

    public void changeGoods(ShowGoods showGoods, int goodsId, Connection conn) throws SQLException {
        goodsDAO.update(checkedUpdateGoods(showGoods, goodsId), conn);
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

    public ShowGoods getShowGoods(int goodsId) throws SQLException {
        ShowGoods showGoods = new ShowGoods();
        showGoods.setGoods(findByGoodsId(goodsId));
        showGoods.setProduct(productService.findByProductId(findByGoodsId(goodsId).getProductId()));
        return showGoods;
    }

    public Goods checkedUpdateGoods(ShowGoods showGoods, int goodsId) throws SQLException {
        Goods goods = findByGoodsId(goodsId);
        if (!goods.getGoodsName().equals(showGoods.getGoods().getGoodsName())) {
            goods.setGoodsName(showGoods.getGoods().getGoodsName());
        }
        if (!goods.getGoodsType().equals(showGoods.getGoods().getGoodsType())) {
            goods.setGoodsType(showGoods.getGoods().getGoodsType());
        }
        if (goods.getGoodsPrice() == showGoods.getGoods().getGoodsPrice()) {
            goods.setGoodsPrice(showGoods.getGoods().getGoodsPrice());
        }
        productService.checkedUpdateProduct(showGoods, goods.getProductId());

        return goods;
    }
}
