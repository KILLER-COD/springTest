package com.mkyong.controllers;

import com.mkyong.address.service.AddressService;
import com.mkyong.shops.model.AllShopsData;
import com.mkyong.shops.model.NewShopInfo;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.model.ShopsInfo;
import com.mkyong.shops.service.ShopsInfoService;
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
    private final ShopsInfoService shopsInfoService;
    private final AddressService addressService;

    @Autowired
    public ShopsController(ShopsService shopsService, ShopsInfoService shopsInfoService, AddressService addressService) {
        this.shopsService = shopsService;
        this.addressService = addressService;
        this.shopsInfoService = shopsInfoService;
    }

    @GetMapping()
    public String index(Model model) throws SQLException {
        model.addAttribute("shopsList", shopsService.getAllShops());
        return "shops/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) throws SQLException {
        Shops shops = shopsService.findByShopsId(id);
        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(shops.getShopInfoId());
        NewShopInfo newShopInfo = NewShopInfo.builder()
                .shopsInfo(shopsInfo)
                .address(addressService.findByAddressId(shopsInfo.getAddressId()))
                .build();

        AllShopsData allShopsData = AllShopsData.builder()
                .shops(shops)
                .addressShop(addressService.findByAddressId(shops.getShopAddressId()))
                .newShopInfo(newShopInfo)
                .build();
        model.addAttribute("shopsData", allShopsData);
        return "shops/show";
    }

    @GetMapping("/new")
    public String newShops(@ModelAttribute("allShopsData") AllShopsData allShopsData, Model model) throws SQLException {
        model.addAttribute("shopsInfoList", shopsInfoService.getAllShopsInfo());
        return "shops/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("allShopsData") AllShopsData allShopsData) {
        System.out.println(allShopsData);
        int shopAddressId = addressService.addNewAddress(allShopsData.getAddressShop());
        Shops shops = Shops.builder()
                .shopName(allShopsData.getShops().getShopName())
                .shopInfoId(allShopsData.getShops().getShopInfoId())
                .shopAddressId(shopAddressId)
                .build();
        shopsService.addNewShops(shops, null);
        return "redirect:/shops";
    }
}
