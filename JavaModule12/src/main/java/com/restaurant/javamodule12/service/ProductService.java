package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.entity.ProductParameter;
import com.restaurant.javamodule12.mapper.ParameterMapper;
import com.restaurant.javamodule12.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> getProductsBuCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public Optional<Product> getProductByName(String productName) {
        return Optional.ofNullable(productRepository.getProductByName(productName));
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.searchDistinctPart(keyword, pageable);
    }

    public Product changeCategoryToProduct(Product product, Category category, Map<String, String> newParametersToProducts) {
        List<ProductParameter> newParameters = ParameterMapper.toProductParameterEntityList(product, category.getParameters(), newParametersToProducts);

        product.setCategory(category);
        product.getProductParameters().clear();
        product.getProductParameters().addAll(newParameters);

        return productRepository.save(product);
    }

    public Product updateProduct(Product product, String newProductName, Integer newQuantity, BigDecimal newPrice) {
        if(newProductName != null && !newProductName.isEmpty()) {
            product.setName(newProductName);
        }
        if(newQuantity != null) {
            product.setQuantity(newQuantity);
        }
        if(newPrice != null) {
            product.setPrice(newPrice);
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
