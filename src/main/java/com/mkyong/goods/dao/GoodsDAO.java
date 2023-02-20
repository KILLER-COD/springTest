package com.mkyong.goods.dao;

import com.mkyong.goods.model.Goods;

public interface GoodsDAO {
    public void insert(Goods goods);
    public Goods findByGoodsId(int goods_id);
}
