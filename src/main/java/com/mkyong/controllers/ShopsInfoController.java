package com.mkyong.controllers;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
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
    private final AddressService addressService;

    @Autowired
    public ShopsInfoController(ShopsInfoService shopsInfoService, AddressService addressService) {
        this.shopsInfoService = shopsInfoService;
        this.addressService = addressService;
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
        model.addAttribute("address", addressService.findByAddressId(shopsInfo.getAddressId()));
        return "shopsInfo/show";
    }

    @GetMapping("/new")
    public String newAddress(@ModelAttribute("newShopsInfo") NewShopInfo newShopInfo) {
        return "shopsInfo/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("newShopsInfo") NewShopInfo newShopInfo) {
        System.out.println(newShopInfo);
        Address address = newShopInfo.getAddress();
        int addressId = addressService.addNewAddress(address);

        ShopsInfo shopsInfo = newShopInfo.getShopsInfo();
        shopsInfo.setAddressId(addressId);
        shopsInfoService.addNewShopsInfo(shopsInfo);

        return "redirect:/shopsInfo";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsInfoService.deleteShopsInfo(id);
        return "redirect:/shopsInfo";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(id);
        Address address = addressService.findByAddressId(shopsInfo.getAddressId());
        NewShopInfo newShopsInfo = new NewShopInfo();
        newShopsInfo.setShopsInfo(shopsInfo);
        newShopsInfo.setAddress(address);
        System.out.println(newShopsInfo);
        model.addAttribute("newShopsInfo", newShopsInfo);

        return "shopsInfo/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("newShopsInfo") NewShopInfo newShopInfo, @PathVariable("id") int id) throws SQLException {
        shopsInfoService.changeShopsInfo(newShopInfo, id, null);
        return "redirect:/shopsInfo";
    }

}