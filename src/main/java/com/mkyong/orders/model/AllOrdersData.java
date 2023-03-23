package com.mkyong.orders.model;

import com.mkyong.shops.model.NewShopInfo;
import com.mkyong.shops.model.Shops;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllOrdersData {
    private Orders orders;
    private OrdersGoods ordersGoods;
    private Shops shops;
    private NewShopInfo newShopInfo;
}