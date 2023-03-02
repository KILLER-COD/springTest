package com.mkyong.goods.dao;

import com.mkyong.goods.model.Goods;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GoodsDAO {
    int insert(Goods goods, Connection conn);

    Goods findByGoodsId(int goods_id);

    void update(Goods goods, int goodsId, Connection conn) throws SQLException;

    //    void update(String goodsName,String goodsType ,double goodsPrice,int productId, int goodsId,Connection conn) throws SQLException;
    void deleteHard(int goodsId) throws SQLException;

    void deleteSoft(int goodsId) throws SQLException;

    ArrayList<Goods> getAllGoods() throws SQLException;

    ArrayList<Goods> getAllDeletedGoods() throws SQLException;
}
