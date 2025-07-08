package com.data.sesson01_javawebservice.controller;

import com.data.sesson01_javawebservice.entity.Product;
import com.data.sesson01_javawebservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "listProduct";
    }

    @GetMapping("/add-product")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/update-product/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "updateProduct";
        }
        return "redirect:/products";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product updatedProduct) {
        Product existing = productRepository.findById(id);
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            productRepository.update(existing);
        }
        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.delete(id);
        return "redirect:/products";
    }
}
