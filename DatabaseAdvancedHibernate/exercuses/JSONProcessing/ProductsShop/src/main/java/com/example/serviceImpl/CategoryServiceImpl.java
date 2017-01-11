package com.example.serviceImpl;

import com.example.domain.dto.CategoryDto;
import com.example.domain.dto.CategoryInfoDto;
import com.example.domain.models.Category;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void persist(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public CategoryDto getByName(String name) {
        Category category = this.categoryRepository.findByName(name);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    @Override
    public List<CategoryInfoDto> getAllByProductsCount() {
        return this.categoryRepository.findAllByProductsCount();
    }
}
