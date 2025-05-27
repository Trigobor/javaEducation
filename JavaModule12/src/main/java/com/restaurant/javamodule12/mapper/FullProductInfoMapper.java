package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestCategoryDTO;
import com.restaurant.javamodule12.DTO.RequestCreateUpdateProductParameterDTO;
import com.restaurant.javamodule12.DTO.RequestParameterDTO;
import com.restaurant.javamodule12.DTO.ResponseFullProductInfoDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullProductInfoMapper {
    public static Product toProductEntity(RequestCreateUpdateProductParameterDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getProductName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());
        return product;
    }

    public static Category toCategoryEntity(RequestCreateUpdateProductParameterDTO requestDTO) {
        Category category = new Category();
        category.setName(requestDTO.getCategoryName());
        return category;
    }

    public static Map<String, String> toProductParameterMap(RequestCreateUpdateProductParameterDTO requestDTO) {
        if (requestDTO.getParametersToProducts() == null) {
            return new HashMap<>();
        }

        return new HashMap<>(requestDTO.getParametersToProducts());

    }

    public static ResponseFullProductInfoDTO toResponseFullProductInfoDTO(Product product, Category category, Map<String, String> productParameterMap) {
        ResponseFullProductInfoDTO responseDTO = new ResponseFullProductInfoDTO();
        responseDTO.setProductName(product.getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setQuantity(product.getQuantity());
        responseDTO.setCategoryName(category.getName());
        if (productParameterMap == null || productParameterMap.isEmpty()) {
            responseDTO.setParametersToProducts(new HashMap<>());
            return responseDTO;
        }
        responseDTO.setParametersToProducts(productParameterMap);
        return responseDTO;
    }

    public static RequestCategoryDTO toCategoryDTO(RequestCreateUpdateProductParameterDTO requestDTO) {
        RequestCategoryDTO requestCategoryDTO = new RequestCategoryDTO();
        requestCategoryDTO.setCategoryName(requestDTO.getCategoryName());
        return requestCategoryDTO;
    }

    public static List<RequestParameterDTO> toListParameters(RequestCreateUpdateProductParameterDTO requestDTO) {
        List<RequestParameterDTO> result = new ArrayList<>();

        if (requestDTO.getParametersToProducts() != null && !requestDTO.getParametersToProducts().isEmpty()) {
            for (Map.Entry<String, String> entry : requestDTO.getParametersToProducts().entrySet()) {
                String name = entry.getKey();
                RequestParameterDTO param = new RequestParameterDTO(
                        name,
                        requestDTO.getCategoryName(),
                        entry.getValue()
                );
                result.add(param);
            }
        }
        return result;
    }
}
