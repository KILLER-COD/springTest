package com.mkyong.controllers;

import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.shops.model.ShopAllData;
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
    public String newShops(@ModelAttribute("shopAllData") ShopAllData shopAllData, Model model) {
        return "shops/new";
    }

    @PostMapping
    public String create(@ModelAttribute("shopAllData") ShopAllData shopAllData) {
        System.out.println(shopAllData);
        shopsService.addNewShops(shopAllData, null);
        return "redirect:/shops";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
//        ShopAllData shopAllData = joinByQueryDAO.getSingleShopData(id);
//        shopAllData.setPersonPhoneTestMap(shopsService.testPersonContactMap(id));
//        model.addAttribute("allShopData", shopAllData);
        return "shops/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("allShopData") ShopAllData shopAllData, @PathVariable("id") int id) throws SQLException {
        shopsService.testGetPersonDataAndPhoneData(id);
//        shopsService.changeShops(shopAllData, id, null);
        return "redirect:/shops";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsService.deleteShops(id);
        return "redirect:/shops";
    }
}
