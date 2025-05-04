package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestProductDTO;
import com.restaurant.javamodule12.DTO.ResponseProductDTO;
import com.restaurant.javamodule12.entity.Product;

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
}
