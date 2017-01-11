package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Product;
import softuni.repositories.ProductDao;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void persist(Product product) {
        this.productDao.saveAndFlush(product);
    }

    @Override
    public List<Product> getAll() {
        return this.productDao.findAll();
    }
}
