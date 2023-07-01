package com.mkyong.controllers;

import com.mkyong.methods.JoinByQueryDAO;
import com.mkyong.shops.model.PersonInfoData;
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
@RequestMapping("personData")
@RequiredArgsConstructor
public class PersonDataController {
    private final ShopsService shopsService;
    private final JoinByQueryDAO joinByQueryDAO;

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        Map<ShopPersonData, List<ShopPersonContact>> testMap = shopsService.testPersonContactAndDataMap(id);
        model.addAttribute("data", testMap);
        return "personData/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("allShopData") PersonInfoData personInfoData, @PathVariable("id") int id) throws SQLException {

        return "redirect:/personData";
    }

}
