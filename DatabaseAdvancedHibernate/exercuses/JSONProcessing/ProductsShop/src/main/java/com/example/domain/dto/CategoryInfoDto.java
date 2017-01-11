package com.example.domain.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryInfoDto {
    @Expose
    private String name;
    @Expose
    private Integer productsCount;
    @Expose
    private Double averagePrice;
    @Expose
    private BigDecimal totalPrice;

    public CategoryInfoDto(String name, Integer productsCount, Double averagePrice, BigDecimal totalPrice) {
        this.name = name;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
