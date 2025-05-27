package com.restaurant.javamodule12.repository;

import com.restaurant.javamodule12.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);

    @Query("SELECT DISTINCT d FROM Category d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    Page<Category> searchDistinctPart(@Param("namePart") String namePart, Pageable pageable);
}
