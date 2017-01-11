package com.example.service;

import com.example.domain.dto.CategoryDto;
import com.example.domain.dto.CategoryInfoDto;

import java.util.List;

public interface CategoryService {
    void persist(CategoryDto categoryDto);

    CategoryDto getByName(String name);

    List<CategoryInfoDto> getAllByProductsCount();
}
