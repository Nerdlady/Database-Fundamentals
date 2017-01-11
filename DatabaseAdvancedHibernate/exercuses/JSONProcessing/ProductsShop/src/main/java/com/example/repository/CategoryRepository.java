package com.example.repository;

import com.example.domain.dto.CategoryInfoDto;
import com.example.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);

    @Query("SELECT new com.example.domain.dto.CategoryInfoDto(c.name,c.products.size,AVG(p.price),SUM(p.price)) FROM Category AS c " +
            "INNER JOIN c.products AS p " +
            "GROUP BY c.id " +
            "ORDER BY c.products.size")
    List<CategoryInfoDto> findAllByProductsCount();
}
