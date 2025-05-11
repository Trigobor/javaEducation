package com.restaurant.javamodule12.repository;

import com.restaurant.javamodule12.entity.Category;
import com.restaurant.javamodule12.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByCategoryId(Long category_id);

    Product getProductByName(String productName);

    @Query("SELECT DISTINCT d FROM Product d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    Page<Product> searchDistinctPart(@Param("namePart") String namePart, Pageable pageable);
}
