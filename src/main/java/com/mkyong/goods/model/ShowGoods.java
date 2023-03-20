package com.mkyong.goods.model;

import com.mkyong.product.model.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowGoods {
    private Goods goods;
    private Product product;

    @Override
    public String toString() {
        return "ShowGoods{" +
                "goods=" + goods +
                ", product=" + product +
                '}';
    }
}
