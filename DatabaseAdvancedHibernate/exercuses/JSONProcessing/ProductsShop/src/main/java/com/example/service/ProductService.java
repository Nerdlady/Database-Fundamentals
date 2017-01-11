package com.example.service;

import com.example.domain.dto.ProductDto;
import com.example.domain.dto.ProductWithBuyerDto;
import com.example.domain.dto.ProductWithSellerDto;
import com.example.domain.dto.SoldProducts;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void persist(ProductDto productDto);

    List<ProductWithSellerDto> getAllWherePriceBetweenAndNoBuyer(BigDecimal from, BigDecimal to);

    List<ProductWithBuyerDto> getAllBySellerNameAndBuyerIsNoNull(String lastName);

    SoldProducts getAllSoldProductsBySellerName(String lastName);
}
