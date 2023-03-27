package com.mkyong.controllers;

import com.mkyong.orders.model.OrdersGoods;
import com.mkyong.orders.service.OrdersGoodsService;
import com.mkyong.orders.service.OrdersService;
import com.mkyong.shops.service.ShopsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/ordersGoods")
@RequiredArgsConstructor
public class OrdersGoodsController {

    private final ShopsService shopsService;
    private final OrdersGoodsService ordersGoodsService;
    private final OrdersService ordersService;


    @GetMapping()
    public String index(Model model) throws SQLException {
        model.addAttribute("ordersGoodsList", ordersGoodsService.getAllOrdersGoods());
        return "ordersGoods/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) throws SQLException {
        List<OrdersGoods> ordersGoods = ordersGoodsService.findByOrdersGoodsOrdersId(id);
        model.addAttribute("ordersGoodsList", ordersGoods);
        return "ordersGoods/show";
    }

    @GetMapping("/orders/{id}")
    public String showOrders(Model model, @PathVariable("id") int id) throws SQLException {
        List<OrdersGoods> ordersGoods = ordersGoodsService.findByOrdersGoodsOrdersId(id);
        model.addAttribute("ordersGoodsList", ordersGoods);
        return "ordersGoods/show";
    }

}
