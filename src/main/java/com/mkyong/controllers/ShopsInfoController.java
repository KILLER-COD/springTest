package com.mkyong.controllers;

import com.mkyong.shops.model.ShopInfoData;
import com.mkyong.shops.model.ShopsInfo;
import com.mkyong.shops.service.ShopsInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/shops/shopsInfo")
@RequiredArgsConstructor
public class ShopsInfoController {
    private final ShopsInfoService shopsInfoService;

    @GetMapping
    public String index(Model model) throws SQLException {
        model.addAttribute("shopsInfoList", shopsInfoService.getAllShopsInfo());
        return "shops/shopsInfo/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(id);
        model.addAttribute("shopsInfo", shopsInfo);
        model.addAttribute("address", shopsInfoService.getShopsInfoAddressInfo(id));
        return "shops/shopsInfo/show";
    }

    @GetMapping("/new")
    public String newAddress(@ModelAttribute("newShopsInfo") ShopInfoData shopInfoData) {
        return "shopsInfo/new";
    }

//    @PostMapping
//    public String create(@ModelAttribute("newShopsInfo") ShopInfoData newShopInfo) {
//        shopsInfoService.setNewShopsInfoData(newShopInfo);
//        return "redirect:/shopsInfo";
//    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsInfoService.deleteShopsInfo(id);
        return "redirect:shops/shopsInfo";
    }

//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
//        model.addAttribute("newShopsInfo", shopsInfoService.getShowShopsInfoData(id));
//        return "shopsInfo/edit";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("newShopsInfo") ShopsInfo newShopInfo, @PathVariable("id") int id) throws SQLException {
        shopsInfoService.changeShopsInfo(newShopInfo, id, null);
        return "redirect:shops/shopsInfo";
    }

}