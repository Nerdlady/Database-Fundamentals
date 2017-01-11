package com.example.serviceImpl;

import com.example.domain.dto.*;
import com.example.domain.models.User;
import com.example.repository.UserRepository;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final ProductService productService;

    public UserServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void persist(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDto getByName(String name) {
        User user = this.userRepository.findByLastName(name).get(0);
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAge(user.getAge());
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public List<UserWithSoldProductsAndBuyerDto> getAllWithSoldProductsAndBuyer() {
        List<UserWithSoldProductsAndBuyerDto> sellers = this.userRepository.findAllWithSoldProducts();
        for (UserWithSoldProductsAndBuyerDto seller : sellers) {
            List<ProductWithBuyerDto> products = this.productService.getAllBySellerNameAndBuyerIsNoNull(seller.getLastName());
            seller.setSoldProducts(products);
        }
        return sellers;
    }

    @Override
    public AllUsersWIthSoldProducts getAllWithSoldProducts() {
        List<UserWithSoldProductsDto> userWithSoldProductsDtos = this.userRepository.findAllWithSoldProductsOrdered();
        for (UserWithSoldProductsDto userWithSoldProductsDto : userWithSoldProductsDtos) {
            SoldProducts soldProducts = this.productService.getAllSoldProductsBySellerName(userWithSoldProductsDto.getLastName());
            userWithSoldProductsDto.setSoldProducts(soldProducts);
        }
        AllUsersWIthSoldProducts allUsersWIthSoldProducts = new AllUsersWIthSoldProducts(userWithSoldProductsDtos.size(),userWithSoldProductsDtos);
        return allUsersWIthSoldProducts ;
    }
}
