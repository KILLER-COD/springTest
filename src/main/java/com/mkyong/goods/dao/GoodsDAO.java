package com.mkyong.goods.dao;

import com.mkyong.goods.model.Goods;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GoodsDAO {
    int insert(Goods goods, Connection conn);

    Optional<Goods> findByGoodsId(int goods_id);

    void update(Goods goods, Connection conn) throws SQLException;

    //    void update(String goodsName,String goodsType ,double goodsPrice,int productId, int goodsId,Connection conn) throws SQLException;
    void deleteHard(int goodsId) throws SQLException;

    void deleteSoft(int goodsId) throws SQLException;

    List<Goods> getAllGoods() throws SQLException;

    List<Goods> getAllDeletedGoods() throws SQLException;
}
