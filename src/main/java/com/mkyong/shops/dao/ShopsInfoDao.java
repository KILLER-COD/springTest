package com.mkyong.shops.dao;

import com.mkyong.shops.model.ShopsInfo;

public interface ShopsInfoDao {
    public void insert(ShopsInfo shopsInfo);
    public ShopsInfo findByShopsInfoId(int shops_info_id);
}
