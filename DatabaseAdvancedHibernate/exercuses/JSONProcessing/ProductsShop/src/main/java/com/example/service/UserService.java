package com.example.service;

import com.example.domain.dto.AllUsersWIthSoldProducts;
import com.example.domain.dto.UserDto;
import com.example.domain.dto.UserWithSoldProductsAndBuyerDto;

import java.util.List;

public interface UserService {
    void persist(UserDto userDto);

    UserDto getByName(String name);

    List<UserWithSoldProductsAndBuyerDto> getAllWithSoldProductsAndBuyer();

    AllUsersWIthSoldProducts getAllWithSoldProducts();
}
