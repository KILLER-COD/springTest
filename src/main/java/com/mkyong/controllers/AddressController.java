package com.mkyong.controllers;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressDAO addressDAO;

    @Autowired
    public AddressController(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @GetMapping()
    public String index(Model model) throws SQLException {
        model.addAttribute("addressList", addressDAO.getAllAddress());
        return "address/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("address", addressDAO.findByAddressId(id));
        return "address/show";
    }

    @GetMapping("/new")
    public String newAddress(@ModelAttribute("address") Address address) {
        return "address/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("address") Address address) {
        addressDAO.insert(address, null);
        return "redirect:/address";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("address", addressDAO.findByAddressId(id));
        return "address/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Address address, @PathVariable("id") int id) throws SQLException {

        addressDAO.update(address, id, null);
        return "redirect:/address";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        addressDAO.deleteSoft(id);
        return "redirect:/address";
    }
}