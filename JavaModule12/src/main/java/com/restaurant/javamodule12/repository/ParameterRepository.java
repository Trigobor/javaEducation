package com.restaurant.javamodule12.repository;

import com.restaurant.javamodule12.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository  extends JpaRepository<Parameter, Long> {
    public List<Parameter> addParameters();
}
