package com.mkyong.controllers;

import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.shops.model.ShopAllData;
import com.mkyong.shops.model.ShopPersonContact;
import com.mkyong.shops.model.ShopPersonData;
import com.mkyong.shops.model.TestShopData;
import com.mkyong.shops.service.ShopsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("personData")
@RequiredArgsConstructor
public class PersonDataController {
    private final ShopsService shopsService;
    private final JoinByQueryDAO joinByQueryDAO;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("personList", joinByQueryDAO.getAllShopData());
        return "personData/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        Map<ShopPersonData, List<ShopPersonContact>> testMap = shopsService.testPersonContactAndDataMap(id);
        ShopAllData shopAllData = joinByQueryDAO.getSingleShopData(id);
        shopAllData.setPersonPhoneTestMap(testMap);
        int maxPersonSize = testMap.size() + 1;
        model.addAttribute("testMap", shopAllData);
        model.addAttribute("maxPerson", maxPersonSize);
        return "personData/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("allShopData") TestShopData testShopData, @PathVariable("id") int id) throws SQLException {
        System.out.println(testShopData);
        List<String[]> newPersonPhone = new ArrayList<>(testShopData.getPersonPhone());
        testShopData.getPersonPhone().forEach(
                phoneNull -> {
                    if (phoneNull == null) {
                        newPersonPhone.remove(phoneNull);
                    }
                }
        );

        newPersonPhone.forEach(
                newPhone -> {
                    for (int i = 0; i < newPhone.length; i++) {
                        System.out.println(newPhone[i]);
                    }
                }
        );
        System.out.println("-----------");
        System.out.println(Arrays.toString(testShopData.getPersonName()));
        System.out.println(Arrays.toString(testShopData.getPersonEmail()));

//        shopsService.testGetPersonDataAndPhoneData(testShopData,id);
//        shopsService.changeShops(shopAllData, id, null);
        return "redirect:/personData";
    }

}
