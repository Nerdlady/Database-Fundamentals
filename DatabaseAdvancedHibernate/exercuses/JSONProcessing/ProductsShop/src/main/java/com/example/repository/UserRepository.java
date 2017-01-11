package com.example.repository;

import com.example.domain.dto.UserWithSoldProductsAndBuyerDto;
import com.example.domain.dto.UserWithSoldProductsDto;
import com.example.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByLastName(String name);

    @Query("SELECT new com.example.domain.dto.UserWithSoldProductsAndBuyerDto(s.firstName,s.lastName) FROM User AS s INNER JOIN s.soldProducts AS sp " +
            "WHERE s.soldProducts.size > 0 " +
            "AND sp.buyer.id IS NOT NULL " +
            "GROUP BY s.id " +
            "ORDER BY s.lastName ASC, s.firstName ASC")
    List<UserWithSoldProductsAndBuyerDto> findAllWithSoldProducts();

    @Query("SELECT new com.example.domain.dto.UserWithSoldProductsDto(s.firstName,s.lastName,s.age) FROM User AS s INNER JOIN s.soldProducts AS sp " +
            "WHERE s.soldProducts.size > 0 " +
            "AND sp.buyer.id IS NOT NULL " +
            "GROUP BY s.id " +
            "ORDER BY s.soldProducts.size DESC, s.lastName ASC")
    List<UserWithSoldProductsDto> findAllWithSoldProductsOrdered();
}
