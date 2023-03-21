package com.mkyong.controllers;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public String index(Model model) throws SQLException {
        model.addAttribute("addressList", addressService.getAllAddress());
        return "address/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("address", addressService.findByAddressId(id));
        return "address/show";
    }

    @GetMapping("/new")
    public String newAddress(@ModelAttribute("address") Address address) {
        return "address/new";
    }

    @PostMapping
    public String create(@ModelAttribute("address") Address address) {
        addressService.addNewAddress(address);
        return "redirect:/address";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("address", addressService.findByAddressId(id));
        return "address/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("address") Address address, @PathVariable("id") int id) throws SQLException {
        addressService.changeAddress(address, id);
        return "redirect:/address";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        addressService.deleteAddress(id);
        return "redirect:/address";
    }
}