package com.mkyong.product.dao;

import com.mkyong.product.model.Product;

public interface ProductDAO {
    public void  insert(Product product);
    public void findByProductId(int product_id);
}
