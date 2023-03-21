package com.mkyong.controllers;

import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.shops.model.GetShopAllData;
import com.mkyong.shops.service.ShopsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("shops")
@RequiredArgsConstructor
public class ShopsController {
    private final ShopsService shopsService;
    private final JoinByQueryDAO joinByQueryDAO;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("shopsList", joinByQueryDAO.getAllShopData());
        return "shops/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("shopSingleData", joinByQueryDAO.getSingleShopData(id));
        return "shops/show";
    }

    @GetMapping("/new")
    public String newShops(@ModelAttribute("shopAllData") GetShopAllData getShopAllData) {
        return "shops/new";
    }

    @PostMapping
    public String create(@ModelAttribute("shopAllData") GetShopAllData getShopAllData) {
        shopsService.addNewShops(getShopAllData, null);
        return "redirect:/shops";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("allShopData", joinByQueryDAO.getSingleShopData(id));
        return "shops/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("allShopData") GetShopAllData getShopAllData, @PathVariable("id") int id) throws SQLException {
        shopsService.changeShops(getShopAllData, id, null);
        return "redirect:/shops";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsService.deleteShops(id);
        return "redirect:/shops";
    }
}
