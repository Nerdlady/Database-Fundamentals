package com.example.serviceImpl;

import com.example.domain.dto.*;
import com.example.domain.models.Category;
import com.example.domain.models.Product;
import com.example.domain.models.User;
import com.example.repository.ProductRepository;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final UserService userService;

    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.userService = new UserServiceImpl(this);
        this.categoryService = categoryService;
    }

    @Override
    public void persist(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        User buyer = new User();
        if (productDto.getBuyer() != null) {
            UserDto buyerDto = this.userService.getByName(productDto.getBuyer().getLastName());
            buyer.setId(buyerDto.getId());
            buyer.setLastName(buyerDto.getLastName());
            product.setBuyer(buyer);

        }

        User seller = new User();
        UserDto sellerDto = this.userService.getByName(productDto.getSeller().getLastName());
        seller.setId(sellerDto.getId());
        seller.setLastName(sellerDto.getLastName());

        product.setSeller(seller);

        Set<Category> categories = new HashSet<>();
        for (CategoryDto categoryDto : productDto.getCategories()) {
            CategoryDto categoryDto2 = this.categoryService.getByName(categoryDto.getName());
            Category category = new Category();
            category.setName(categoryDto2.getName());
            category.setId(categoryDto2.getId());
            categories.add(category);
        }
        product.setCategories(categories);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductWithSellerDto> getAllWherePriceBetweenAndNoBuyer(BigDecimal from, BigDecimal to) {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNull(from, to);
    }

    @Override
    public List<ProductWithBuyerDto> getAllBySellerNameAndBuyerIsNoNull(String lastName) {
        return this.productRepository.findAllBySellerNameAndBuyerIsNotNull(lastName);
    }

    @Override
    public SoldProducts getAllSoldProductsBySellerName(String lastName) {
        List<ProductDto> products = this.productRepository.findAllBySeller(lastName);
        return new SoldProducts(products.size(),products);
    }
}
