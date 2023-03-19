package com.mkyong.controllers;

import com.mkyong.goods.model.Goods;
import com.mkyong.goods.model.NewGoods;
import com.mkyong.goods.service.GoodsService;
import com.mkyong.product.model.Product;
import com.mkyong.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private final ProductService productService;
    private final GoodsService goodsService;

    @Autowired
    private GoodsController(ProductService productService, GoodsService goodsService) {
        this.productService = productService;
        this.goodsService = goodsService;
    }

    @GetMapping()
    public String index(Model model) throws SQLException {
        model.addAttribute("goodsList", goodsService.getAllGoods());
        return "goods/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
        NewGoods newGoods = new NewGoods();
        Goods goods = goodsService.findByGoodsId(id);
        newGoods.setGoods(goods);
        newGoods.setProduct(productService.findByProductId(goods.getProductId()));
        model.addAttribute("showGoods", newGoods);
        return "goods/show";
    }

    @GetMapping("/new")
    public String newGoods(@ModelAttribute("newGoods") NewGoods newGoods) {
        return "goods/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("newGoods") NewGoods newGoods) throws InterruptedException {
        Goods goods = newGoods.getGoods();
        Product product = newGoods.getProduct();
        int productId = productService.addNewProduct(product, null);
        goods.setProductId(productId);
        goodsService.addNewGoods(goods);

        return "redirect:/goods";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        Goods goods = goodsService.findByGoodsId(id);
        Product product = productService.findByProductId(goods.getProductId());
        NewGoods newGoods = NewGoods.builder()
                .goods(goods)
                .product(product)
                .build();
        model.addAttribute("newGoods", newGoods);
        return "goods/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("newGoods") NewGoods newGoods, @PathVariable("id") int id) throws SQLException {
        Goods goods = goodsService.findByGoodsId(id);
        Product product = productService.findByProductId(goods.getProductId());
        if (!goods.getGoodsName().equals(newGoods.getGoods().getGoodsName())) {
            goods.setGoodsName(newGoods.getGoods().getGoodsName());
        }
        if (!goods.getGoodsType().equals(newGoods.getGoods().getGoodsType())) {
            goods.setGoodsType(newGoods.getGoods().getGoodsType());
        }
        if (goods.getGoodsPrice() == newGoods.getGoods().getGoodsPrice()) {
            goods.setGoodsPrice(newGoods.getGoods().getGoodsPrice());
        }
        if (!product.getProductName().equals(newGoods.getProduct().getProductName())) {
            product.setProductName(newGoods.getProduct().getProductName());
        }
        if (!product.getProductType().equals(newGoods.getProduct().getProductType())) {
            product.setProductType(newGoods.getProduct().getProductType());
        }

        productService.changeProduct(product, goods.getProductId());
        goodsService.changeGoods(goods, null);
        return "redirect:/goods";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        goodsService.deleteGoods(id);
        return "redirect:/goods";
    }

}
