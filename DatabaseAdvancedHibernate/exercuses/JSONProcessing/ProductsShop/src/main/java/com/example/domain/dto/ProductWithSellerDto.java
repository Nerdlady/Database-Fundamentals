package com.example.domain.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductWithSellerDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String sellerName;

    public ProductWithSellerDto(String name, BigDecimal price, String sellerName) {
        this.name = name;
        this.price = price;
        this.sellerName = sellerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
