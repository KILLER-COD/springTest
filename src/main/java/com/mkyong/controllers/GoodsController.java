package com.mkyong.controllers;

import com.mkyong.goods.model.ShowGoods;
import com.mkyong.goods.service.GoodsService;
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
        model.addAttribute("showGoods", goodsService.getShowGoods(id));
        return "goods/show";
    }

    @GetMapping("/new")
    public String newGoods(@ModelAttribute("showGoods") ShowGoods showGoods) {
        return "goods/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("showGoods") ShowGoods showGoods) throws InterruptedException {
        goodsService.addNewGoods(showGoods);
        return "redirect:/goods";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("showGoods", goodsService.getShowGoods(id));
        return "goods/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("showGoods") ShowGoods showGoods, @PathVariable("id") int id) throws SQLException {
        goodsService.changeGoods(showGoods, id, null);
        return "redirect:/goods";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        goodsService.deleteGoods(id);
        return "redirect:/goods";
    }

}
