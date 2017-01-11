package com.example.terminal;

import com.example.domain.dto.*;
import com.example.parser.JsonParser;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String PATH_TO_READ = "/files/input/json/";
    private static final String PATH_TO_WRITE = "src/main/resources/files/output/json/";

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final JsonParser jsonParser;

    @Autowired
    public Terminal(UserService userService, CategoryService categoryService, ProductService productService, JsonParser jsonParser) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... strings) throws Exception {
     //   this.seedData();

        //3.1
        List<ProductWithSellerDto> productDtos = this.productService.getAllWherePriceBetweenAndNoBuyer(new BigDecimal("500"),new BigDecimal("1000"));
        this.jsonParser.writeToJson(productDtos,PATH_TO_WRITE + "products.json");

        //3.2
        List<UserWithSoldProductsAndBuyerDto> userWithSoldProductsDtos = this.userService.getAllWithSoldProductsAndBuyer();
        this.jsonParser.writeToJson(userWithSoldProductsDtos,PATH_TO_WRITE + "userSoldProducts.json");

        //3.3
        List<CategoryInfoDto> categoryInfoDtos = this.categoryService.getAllByProductsCount();
        this.jsonParser.writeToJson(categoryInfoDtos,PATH_TO_WRITE + "categoriesCount.json");

        //3.4
        AllUsersWIthSoldProducts allUsersWIthSoldProducts = this.userService.getAllWithSoldProducts();
        this.jsonParser.writeToJson(allUsersWIthSoldProducts,PATH_TO_WRITE + "allUsers.json");
    }

    private void seedData() {
        String usersPath = PATH_TO_READ + "users.json";
        UserDto[] userDtos = this.jsonParser.readFromJson(UserDto[].class,usersPath);

        for (UserDto userDto : userDtos) {
            this.userService.persist(userDto);
        }

        String categoriesPath = PATH_TO_READ + "categories.json";
        CategoryDto[] categoryDtos = this.jsonParser.readFromJson(CategoryDto[].class,categoriesPath);

        for (CategoryDto categoryDto : categoryDtos) {
            this.categoryService.persist(categoryDto);
        }

        String productsPath = PATH_TO_READ + "products.json";
        ProductDto[] productDtos = this.jsonParser.readFromJson(ProductDto[].class,productsPath);
        Random random = new Random();

        for (ProductDto productDto : productDtos) {
            int sellerIndex = random.nextInt(userDtos.length);
            int buyerIndex = random.nextInt(userDtos.length);
            int categoryOneIndex = random.nextInt(categoryDtos.length);
            int categoryTwoIndex = random.nextInt(categoryDtos.length);
            productDto.setSeller(userDtos[sellerIndex]);
            Set<CategoryDto> categoryDtosForProduct = new HashSet<>();
            categoryDtosForProduct.add(categoryDtos[categoryOneIndex]);
            categoryDtosForProduct.add(categoryDtos[categoryTwoIndex]);
            productDto.setCategories(categoryDtosForProduct);

            if (random.nextBoolean()){
                productDto.setBuyer(userDtos[buyerIndex]);
            }
            this.productService.persist(productDto);

        }
    }
}
