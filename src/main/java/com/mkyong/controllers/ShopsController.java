package com.mkyong.controllers;

import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.shops.model.ShopAllData;
import com.mkyong.shops.model.ShopPersonContact;
import com.mkyong.shops.model.ShopPersonData;
import com.mkyong.shops.service.ShopsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
        Map<ShopPersonData, List<ShopPersonContact>> testMap = shopsService.testPersonContactAndDataMap(id);
        ShopAllData shopAllData = joinByQueryDAO.getSingleShopData(id);
        shopAllData.setPersonPhoneTestMap(testMap);
        int maxPersonSize = testMap.size() + 1;
        model.addAttribute("shopAllData", shopAllData);
        model.addAttribute("maxPerson", maxPersonSize);
        return "shops/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("shopAllData") ShopAllData shopAllData, @PathVariable("id") int id) throws SQLException {
        System.out.println(shopAllData);
        System.out.println("-----------------------");
        shopAllData.getPersonPhone().forEach(System.out::println);
//        List<String[]> newPersonPhone = new ArrayList<>(shopAllData.getPersonPhone());
//        shopAllData.getPersonPhone().forEach(
//                phoneNull -> {
//                    if (phoneNull == null) {
//                        newPersonPhone.remove(phoneNull);
//                    }
//                }
//        );

//        newPersonPhone.forEach(
//                newPhone -> {
//                    for (int i = 0; i < newPhone.length; i++) {
//                        System.out.println("index-" + i + "= " + newPhone[i]);
//                    }
//                }
//        );
//      /  shopsService.testGetPersonDataAndPhoneData(shopAllData,id);
//        shopsService.changeShops(shopAllData, id, null);
        return "redirect:/shops";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        shopsService.deleteShops(id);
        return "redirect:/shops";
    }
}
