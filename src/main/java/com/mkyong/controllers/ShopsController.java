package com.mkyong.controllers;

import com.mkyong.address.model.Address;
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
//        model.addAttribute("shopsInfoList", shopsInfoService.getAllShopsInfo());
        return "shops/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("allShopsData") AllShopsData allShopsData) {
        System.out.println(allShopsData);
        ShopsInfo shopsInfo = allShopsData.getNewShopInfo().getShopsInfo();
        int shopAddressId = addressService.addNewAddress(allShopsData.getAddressShop());
        int shopInfoAddressId = addressService.addNewAddress(allShopsData.getNewShopInfo().getAddress());
        shopsInfo.setAddressId(shopInfoAddressId);
        int newShopsInfoId = shopsInfoService.addNewShopsInfo(shopsInfo);
        Shops shops = Shops.builder()
                .shopName(allShopsData.getShops().getShopName())
                .shopAddressId(shopAddressId)
                .shopInfoId(newShopsInfoId)
                .build();
        System.out.println(shops);
        shopsService.addNewShops(shops, null);
        return "redirect:/shops";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        Shops shops = shopsService.findByShopsId(id);
        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(shops.getShopInfoId());
        Address addressShop = addressService.findByAddressId(shops.getShopAddressId());
        NewShopInfo newShopInfo = NewShopInfo.builder()
                .shopsInfo(shopsInfo)
                .address(addressService.findByAddressId(shopsInfo.getAddressId()))
                .build();

        AllShopsData allShopsData = AllShopsData.builder()
                .shops(shops)
                .addressShop(addressShop)
                .newShopInfo(newShopInfo)
                .build();
        model.addAttribute("allShopsData", allShopsData);
        model.addAttribute("shopsInfoList", shopsInfoService.getAllShopsInfo());
        System.out.println(allShopsData);
        return "shops/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("allShopsData") AllShopsData allShopsData, @PathVariable("id") int id) throws SQLException {

        //Checked Change Shops name -------------------------------------------------
        Shops shops = shopsService.findByShopsId(id);
        Shops shopsNew = allShopsData.getShops();
        if (!shops.getShopName().equals(shopsNew.getShopName())) {
            shops.setShopName(shopsNew.getShopName());
        }

        // Checked Change Shops Info and Shops Info Address ------------------------
        ShopsInfo shopsInfo = shopsInfoService.findByShopInfoId(shops.getShopInfoId());
        Address address = addressService.findByAddressId(shopsInfo.getAddressId());
        NewShopInfo newShopInfo = allShopsData.getNewShopInfo();

        if (!address.getAddress().equals(newShopInfo.getAddress().getAddress())) {
            address.setAddress(newShopInfo.getAddress().getAddress());
        }
        if (!address.getCity().equals(newShopInfo.getAddress().getCity())) {
            address.setCity(newShopInfo.getAddress().getCity());
        }
        if (!shopsInfo.getShopOwner().equals(newShopInfo.getShopsInfo().getShopOwner())) {
            shopsInfo.setShopOwner(newShopInfo.getShopsInfo().getShopOwner());
        }
        if (shopsInfo.getHvhh() != newShopInfo.getShopsInfo().getHvhh()) {
            shopsInfo.setHvhh(newShopInfo.getShopsInfo().getHvhh());
        }

        //Checked Changes Shops Address -------------------------------------------------
        Address addressShop = addressService.findByAddressId(shops.getShopAddressId());

        if (!addressShop.getAddress().equals(allShopsData.getAddressShop().getAddress())) {
            addressShop.setAddress(allShopsData.getAddressShop().getAddress());
        }
        if (!addressShop.getCity().equals(allShopsData.getAddressShop().getCity())) {
            addressShop.setCity(allShopsData.getAddressShop().getCity());
        }

        shopsService.changeShops(shops, null);
        addressService.changeAddress(addressShop, addressShop.getId());
        shopsInfoService.changeShopsInfo(shopsInfo, shopsInfo.getId(), null);
        addressService.changeAddress(address, address.getId());
        return "redirect:/shops";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsService.deleteShops(id);
        return "redirect:/shops";
    }
}
