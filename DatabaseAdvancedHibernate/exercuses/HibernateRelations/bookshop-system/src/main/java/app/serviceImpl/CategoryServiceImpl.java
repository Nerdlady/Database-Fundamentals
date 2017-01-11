package app.serviceImpl;

import app.dao.CategoryRepository;
import app.domain.Category;
import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void save(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        this.categoryRepository.delete(category);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepository.delete(id);
    }

    @Override
    public Category findCategory(Long id) {
        return this.categoryRepository.findOne(id);
    }

    @Override
    public Iterable<Category> findCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Iterable<Category> findAllByNameIn(String[] names) {
        return this.categoryRepository.findAllByNameIn(names);
    }
}
