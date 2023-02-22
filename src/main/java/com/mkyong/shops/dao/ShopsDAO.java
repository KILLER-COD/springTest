package com.mkyong.shops.dao;

import com.mkyong.shops.model.Shops;

public interface ShopsDAO {
    public void insert(Shops shops);
    public Shops findByShopsId(int shops_id);
}
