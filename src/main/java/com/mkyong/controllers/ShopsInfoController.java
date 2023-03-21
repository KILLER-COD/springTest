package com.mkyong.controllers;

import com.mkyong.shops.model.NewShopInfo;
import com.mkyong.shops.model.ShopsInfo;
import com.mkyong.shops.service.ShopsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/shopsInfo")
public class ShopsInfoController {
    private final ShopsInfoService shopsInfoService;

    @Autowired
    public ShopsInfoController(ShopsInfoService shopsInfoService) {
        this.shopsInfoService = shopsInfoService;
    }


    @GetMapping()
    public String index(Model model) throws SQLException {
        model.addAttribute("shopsInfoList", shopsInfoService.getAllShopsInfo());
        return "shopsInfo/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(id);
        model.addAttribute("shopsInfo", shopsInfo);
        model.addAttribute("address", shopsInfoService.getShopsInfoAddressInfo(id));
        return "shopsInfo/show";
    }

    @GetMapping("/new")
    public String newAddress(@ModelAttribute("newShopsInfo") NewShopInfo newShopInfo) {
        return "shopsInfo/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("newShopsInfo") NewShopInfo newShopInfo) {
        shopsInfoService.setNewShopsInfoData(newShopInfo);
        return "redirect:/shopsInfo";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsInfoService.deleteShopsInfo(id);
        return "redirect:/shopsInfo";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("newShopsInfo", shopsInfoService.getShowShopsInfoData(id));
        return "shopsInfo/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("newShopsInfo") NewShopInfo newShopInfo, @PathVariable("id") int id) throws SQLException {
        shopsInfoService.changeShopsInfo(newShopInfo, id, null);
        return "redirect:/shopsInfo";
    }

}