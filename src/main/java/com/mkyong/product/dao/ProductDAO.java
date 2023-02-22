package com.mkyong.product.dao;

import com.mkyong.product.model.Product;

public interface ProductDAO {
    public void  insert(Product product);
    public Product findByProductId(int product_id);
}
