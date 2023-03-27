package com.mkyong.controllers;

import com.mkyong.goods.model.GoodsAllData;
import com.mkyong.goods.service.GoodsService;
import com.mkyong.methods.JoinByQueryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;
    private final JoinByQueryDAO joinByQueryDAO;

    @GetMapping
    public String index(Model model) throws SQLException {
        model.addAttribute("goodsDataList", goodsService.getAllGoodsData());
        return "goods/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int goodsId, Model model) throws SQLException {
        model.addAttribute("showGoodsData", goodsService.getSingleGoodsData(goodsId));
        return "goods/show";
    }

    @GetMapping("/new")
    public String newGoods(@ModelAttribute("goodsAllData") GoodsAllData goodsAllData, Model model) throws SQLException {
        model.addAttribute("productList", goodsService.getAllProduct());
        return "goods/new";
    }

    @PostMapping
    public String create(@ModelAttribute("getAlGoodsData") GoodsAllData goodsAllData) throws InterruptedException {
        goodsService.addNewGoods(goodsAllData);
        return "redirect:/goods";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("getGoodsAllData", goodsService.getSingleGoodsData(id));
        model.addAttribute("productList", goodsService.getAllProduct());
        return "goods/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("getGoodsAllData") GoodsAllData goodsAllData, @PathVariable("id") int id) throws SQLException {
        goodsService.changeGoods(goodsAllData, null);
        return "redirect:/goods";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        goodsService.deleteGoods(id);
        return "redirect:/goods";
    }

}
