package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }
}
