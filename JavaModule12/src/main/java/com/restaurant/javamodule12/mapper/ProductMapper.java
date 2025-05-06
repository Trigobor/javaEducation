package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestCreateUpdateProductParameterDTO;
import com.restaurant.javamodule12.DTO.RequestProductDTO;
import com.restaurant.javamodule12.DTO.ResponseProductDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.entity.ProductParameter;

import java.util.List;
import java.util.Map;

public class ProductMapper {
    public static ResponseProductDTO toDTO(Product product) {
        ResponseProductDTO dto = new ResponseProductDTO();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        if(!product.getProductParameters().isEmpty()){
            //TODO:надо сделать заполнять в продукте по возможности его параметры
            return dto;
        }
        return dto;
    }

    public static Product toEntity(RequestProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return product;
    }

    public static Product toEntity(RequestCreateUpdateProductParameterDTO productDto, Category category, List<Parameter> expectedParams) {
        Product product = new Product();
        Map<String, String> providedParams = productDto.getParametersToProducts();

        product.setName(productDto.getProductName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        List<ProductParameter> savingParameters = expectedParams.stream()
                .map(Parameter -> {
                    ProductParameter productParam = new ProductParameter();
                    productParam.setProduct(product);
                    productParam.setParameter(Parameter);
                    productParam.setValue(providedParams.get(Parameter.getName()));
                    return productParam;
                })
                .toList();

        product.setProductParameters(savingParameters);
        return product;
    }
}
