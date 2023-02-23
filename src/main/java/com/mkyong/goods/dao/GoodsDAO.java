package com.mkyong.goods.dao;

import com.mkyong.address.model.Address;
import com.mkyong.address.model.AddressCountByCity;
import com.mkyong.goods.model.Goods;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GoodsDAO {
    void insert(Goods goods);
    Goods findByGoodsId(int goods_id);
    void update(Goods goods, int goodsId) throws SQLException;
    void update(String goodsName,String goodsType ,double goodsPrice,int productId, int goodsId) throws SQLException;
    void deleteHard(int goodsId) throws SQLException;
    void deleteSoft(int goodsId) throws SQLException;
    ArrayList<Goods> getAllGoods() throws SQLException;
    ArrayList<Goods> getAllDeletedGoods() throws SQLException;
//    ArrayList<AddressCountByCity> findCountCity();
//    ArrayList<AddressCountByCity> findCountCity(Integer minCountShopInCity);
}
