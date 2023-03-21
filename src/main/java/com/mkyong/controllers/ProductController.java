package com.mkyong.controllers;

import com.mkyong.product.model.Product;
import com.mkyong.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String index(Model model) throws SQLException {
        model.addAttribute("productList", productService.getAllProduct());
        return "product/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("product", productService.findByProductId(id));
        return "product/show";
    }

    @GetMapping("/new")
    public String newAddress(@ModelAttribute("product") Product product) {
        return "product/new";
    }

    @PostMapping
    public String create(@ModelAttribute("product") Product product) {

        productService.addNewProduct(product, null);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("product", productService.findByProductId(id));
        return "product/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product, @PathVariable("id") int id) throws SQLException {
        productService.changeProduct(product, id);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}