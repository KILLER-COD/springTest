package com.mkyong.controllers;

import com.mkyong.shops.model.AllShopsData;
import com.mkyong.shops.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("shops")
public class ShopsController {
    private final ShopsService shopsService;

    @Autowired
    public ShopsController(ShopsService shopsService) {
        this.shopsService = shopsService;
    }

    @GetMapping()
    public String index(Model model) throws SQLException {
        model.addAttribute("shopsList", shopsService.getAllShops());
        return "shops/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("shopsData", shopsService.getShowShopsData(id));
        return "shops/show";
    }

    @GetMapping("/new")
    public String newShops(@ModelAttribute("allShopsData") AllShopsData allShopsData, Model model) throws SQLException {
        return "shops/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("allShopsData") AllShopsData allShopsData) {
        shopsService.addNewShops(allShopsData, null);
        return "redirect:/shops";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("allShopsData", shopsService.getShowShopsData(id));
        model.addAttribute("shopsInfoList", shopsService.getAllShopsInfo());
        return "shops/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("allShopsData") AllShopsData allShopsData, @PathVariable("id") int id) throws SQLException {
        shopsService.changeShops(allShopsData, id, null);
        return "redirect:/shops";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsService.deleteShops(id);
        return "redirect:/shops";
    }
}
