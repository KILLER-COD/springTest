package com.mkyong.controllers;

import com.mkyong.goods.model.Goods;
import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    private final JoinByQueryDAO joinByQueryDAO;


    @GetMapping()
    public String index(Model model) throws SQLException {
//        System.out.println(ordersService.getAllOrdersData());
        model.addAttribute("ordersDataList", ordersService.getAllOrdersData());
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("showOrderAllData", ordersService.getSingleOrdersData(id));
        return "orders/show";
    }

    @GetMapping("/new")
    public String newOrders(@ModelAttribute("orders") Orders orders, Model model) throws SQLException {
        List<Goods> goodsList = ordersService.getAllGoods();
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("shopsList", joinByQueryDAO.allShopsInformation());
        return "orders/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("orders") Orders orders) {
        System.out.println(orders);
        ordersService.addNewOrders(orders, null);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("ordersData") Orders orders, @PathVariable("id") int id) throws SQLException {
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        ordersService.deleteOrders(id);
        return "redirect:/orders";
    }


}
