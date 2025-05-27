package com.restaurant.javamodule12.mapper;

import com.restaurant.javamodule12.DTO.RequestCreateUpdateProductParameterDTO;
import com.restaurant.javamodule12.DTO.RequestProductDTO;
import com.restaurant.javamodule12.DTO.ResponseFullProductInfoDTO;
import com.restaurant.javamodule12.DTO.ResponseProductDTO;
import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Parameter;
import com.restaurant.javamodule12.entity.Product;
import com.restaurant.javamodule12.entity.ProductParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ResponseProductDTO toDTO(Product product) {
        ResponseProductDTO dto = new ResponseProductDTO();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        return dto;
    }

    public static ResponseFullProductInfoDTO toDTOFull(Product product) {
        ResponseFullProductInfoDTO dto = new ResponseFullProductInfoDTO();
        dto.setProductName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        if (product.getCategory() != null) {
            dto.setCategoryName(product.getCategory().getName());
        }

        if (product.getProductParameters() != null && !product.getProductParameters().isEmpty()) {
            Map<String, String> parametersMap = new HashMap<>(
                    product.getProductParameters().stream()
                            .collect(Collectors.toMap(
                                    pp -> pp.getParameter().getName(),
                                    ProductParameter::getValue
                            )));
            dto.setParametersToProducts(parametersMap);
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

    public static List<ResponseProductDTO> toDTOList(List<Product> products) {
        List<ResponseProductDTO> dtoList = new ArrayList<>();
        for (Product product : products) {
            dtoList.add(toDTO(product));
        }
        return dtoList;
    }
}
