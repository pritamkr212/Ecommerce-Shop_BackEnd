package com.IshuVastralay.EcommerceShop.repository;

import com.IshuVastralay.EcommerceShop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    public List<Review>getAllProductsReview(@Param("productId")Long productId);
}
