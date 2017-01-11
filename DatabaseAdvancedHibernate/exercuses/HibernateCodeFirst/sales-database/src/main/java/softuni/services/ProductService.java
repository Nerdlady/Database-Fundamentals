package softuni.services;

import softuni.entities.Product;

import java.util.List;

public interface ProductService {
    void persist(Product product);
    List<Product> getAll();
}
