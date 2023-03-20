package com.mkyong.controllers;

import com.mkyong.goods.model.Goods;
import com.mkyong.goods.service.GoodsService;
import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.orders.model.Orders;
import com.mkyong.orders.service.OrdersGoodsService;
import com.mkyong.orders.service.OrdersService;
import com.mkyong.shops.model.AllShopsData;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final ShopsService shopsService;
    private final OrdersGoodsService ordersGoodsService;
    private final OrdersService ordersService;
    private final GoodsService goodsService;
    private final JoinByQueryDAO joinByQueryDAO;

    @Autowired
    public OrdersController(GoodsService goodsService, ShopsService shopsService, OrdersGoodsService ordersGoodsService, OrdersService ordersService, JoinByQueryDAO joinByQueryDAO) {
        this.goodsService = goodsService;
        this.shopsService = shopsService;
        this.ordersService = ordersService;
        this.ordersGoodsService = ordersGoodsService;
        this.joinByQueryDAO = joinByQueryDAO;
    }

    @GetMapping()
    public String index(Model model) throws SQLException {
        System.out.println(joinByQueryDAO.allOrdersShopsInfo());
        model.addAttribute("ordersDataList", joinByQueryDAO.allOrdersShopsInfo());
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) throws SQLException {
        Shops shops = shopsService.findByShopsId(ordersService.findByOrdersId(id).getShopId());
        model.addAttribute("ordersShops", shops);
        return "shops/show";
    }

    @GetMapping("/new")
    public String newOrders(@ModelAttribute("orders") Orders orders, Model model) throws SQLException {
        List<Goods> goodsList = goodsService.getAllGoods();
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("shopsList", joinByQueryDAO.allShopsInformation());
        return "orders/new";
    }

//    @GetMapping("/new/{id}")
//    public String newOrdersGoods(@ModelAttribute("orders")Orders orders, Model model,@PathVariable("id") int id) throws SQLException {
//        List<Goods> goodsList = goodsService.getAllGoods();
//        model.addAttribute("shopsList", joinByQueryDAO.allShopsInformation());
//        return "orders/new/{id}";
//    }

    @PostMapping()
    public String create(@ModelAttribute("orders") Orders orders) {
//        System.out.println(orders);
//        int orderId = ordersService.addNewOrders(orders,null);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
//        Shops shops = shopsService.findByShopsId(id);
//        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(shops.getShopInfoId());
//        Address addressShop = addressService.findByAddressId(shops.getShopAddressId());
//        NewShopInfo newShopInfo = NewShopInfo.builder()
//                .shopsInfo(shopsInfo)
//                .address(addressService.findByAddressId(shopsInfo.getAddressId()))
//                .build();
//
//        AllShopsData allShopsData = AllShopsData.builder()
//                .shops(shops)
//                .addressShop(addressShop)
//                .newShopInfo(newShopInfo)
//                .build();
//        model.addAttribute("allShopsData", allShopsData);
//        model.addAttribute("shopsInfoList", shopsInfoService.getAllShopsInfo());
//        System.out.println(allShopsData);
        return "shops/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("allShopsData") AllShopsData allShopsData, @PathVariable("id") int id) throws SQLException {

//        //Checked Change Shops name -------------------------------------------------
//        Shops shops = shopsService.findByShopsId(id);
//        Shops shopsNew = allShopsData.getShops();
//        if (!shops.getShopName().equals(shopsNew.getShopName())) {
//            shops.setShopName(shopsNew.getShopName());
//        }
//
//        // Checked Change Shops Info and Shops Info Address ------------------------
//        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(shops.getShopInfoId());
//        Address address = addressService.findByAddressId(shopsInfo.getAddressId());
//        NewShopInfo newShopInfo = allShopsData.getNewShopInfo();
//
//        if (!address.getAddress().equals(newShopInfo.getAddress().getAddress())) {
//            address.setAddress(newShopInfo.getAddress().getAddress());
//        }
//        if (!address.getCity().equals(newShopInfo.getAddress().getCity())) {
//            address.setCity(newShopInfo.getAddress().getCity());
//        }
//        if (!shopsInfo.getShopOwner().equals(newShopInfo.getShopsInfo().getShopOwner())) {
//            shopsInfo.setShopOwner(newShopInfo.getShopsInfo().getShopOwner());
//        }
//        if (shopsInfo.getHvhh() != newShopInfo.getShopsInfo().getHvhh()) {
//            shopsInfo.setHvhh(newShopInfo.getShopsInfo().getHvhh());
//        }
//
//        //Checked Changes Shops Address -------------------------------------------------
//        Address addressShop = addressService.findByAddressId(shops.getShopAddressId());
//
//        if (!addressShop.getAddress().equals(allShopsData.getAddressShop().getAddress())) {
//            addressShop.setAddress(allShopsData.getAddressShop().getAddress());
//        }
//        if (!addressShop.getCity().equals(allShopsData.getAddressShop().getCity())) {
//            addressShop.setCity(allShopsData.getAddressShop().getCity());
//        }
//
//        shopsService.changeShops(shops, null);
//        addressService.changeAddress(addressShop, addressShop.getId());
//        shopsInfoService.changeShopsInfo(shopsInfo, shopsInfo.getId(), null);
//        addressService.changeAddress(address, address.getId());
        return "redirect:/shops";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        ordersService.deleteOrders(id);
        return "redirect:/orders";
    }


}
