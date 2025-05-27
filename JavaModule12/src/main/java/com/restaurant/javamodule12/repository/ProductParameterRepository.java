package com.restaurant.javamodule12.repository;

import com.restaurant.javamodule12.entity.ProductParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductParameterRepository extends JpaRepository<ProductParameter, Long> {
}
