package com.data.sesson01_javawebservice.repository;

import com.data.sesson01_javawebservice.entity.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);

    void update(Product product);

    void delete(Long id);

    Product findById(Long id);

    List<Product> findAll();
}
