package com.IshuVastralay.EcommerceShop.repository;

import com.IshuVastralay.EcommerceShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p"+"WHERE (p.category.name=:category OR :category='')"+"And((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice))"
    +"AND(:minDiscount IS NULL OR p.discountPercent>=:minDiscount)"+"ORDER BY"+
    "CASE WEN :sort = 'price_low' THEN p.discountedPrice END ASC"+
    "CASE WEN :sort = 'price_high' THEN p.discountedPrice END DESC")
    public List<Product>filterProducts(@Param("category")String category,
                                       @Param("minPrice")Integer minPrice,
                                       @Param("maxPrice")Integer maxPrice,
                                       @Param("minDiscount")Integer minDiscout,
                                       @Param("sort")String sort);

}
