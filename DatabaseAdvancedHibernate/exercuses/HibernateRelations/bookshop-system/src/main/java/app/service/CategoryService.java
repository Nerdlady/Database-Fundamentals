package app.service;

import app.domain.Category;

public interface CategoryService {
    void save(Category category);

    void delete(Category category);

    void delete(Long id);

    Category findCategory(Long id);

    Iterable<Category> findCategories();

    Iterable<Category> findAllByNameIn(String[] names);
}
