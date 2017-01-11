package com.example.repository;

import com.example.domain.dto.ProductDto;
import com.example.domain.dto.ProductWithBuyerDto;
import com.example.domain.dto.ProductWithSellerDto;
import com.example.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long>{
    @Query("SELECT new com.example.domain.dto.ProductWithSellerDto(p.name,p.price,CONCAT(p.seller.firstName,' ',p.seller.lastName)) " +
            "FROM Product AS p " +
            "WHERE p.buyer.id IS NULL " +
            "AND p.price BETWEEN :from AND :to  ")
    List<ProductWithSellerDto> findAllByPriceBetweenAndBuyerIsNull(@Param("from") BigDecimal from, @Param("to") BigDecimal to);

    @Query("SELECT new com.example.domain.dto.ProductWithBuyerDto(p.name,p.price,p.buyer.firstName,p.buyer.lastName) FROM Product AS p " +
            "WHERE p.buyer.id IS NOT NULL " +
            "AND p.seller.lastName = :lastName")
    List<ProductWithBuyerDto> findAllBySellerNameAndBuyerIsNotNull(@Param("lastName") String lastName);

    @Query("SELECT new com.example.domain.dto.ProductDto(p.name,p.price) FROM Product AS p " +
            "WHERE p.seller.lastName = :lastName")
    List<ProductDto> findAllBySeller(@Param("lastName") String lastName);
}
