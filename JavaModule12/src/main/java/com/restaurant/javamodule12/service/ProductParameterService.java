package com.restaurant.javamodule12.service;

import com.restaurant.javamodule12.entity.ProductParameter;
import com.restaurant.javamodule12.repository.ProductParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductParameterService {

    private final ProductParameterRepository productParameterRepository;

    @Autowired
    public ProductParameterService(ProductParameterRepository productParameterRepository) {this.productParameterRepository = productParameterRepository;}

    public List<ProductParameter> addProductParameters(List<ProductParameter> productParameters) {
        return productParameterRepository.saveAll(productParameters);
    }
}
