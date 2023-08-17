package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Product;
import com.IshuVastralay.EcommerceShop.model.Review;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.repository.ProductRepository;
import com.IshuVastralay.EcommerceShop.repository.ReviewRepository;
import com.IshuVastralay.EcommerceShop.request.ReviewRequest;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewServiceImplementation implements ReviewService{
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private ProductRepository productRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {
        Product product=productService.findProductById(request.getProductId());
        Review review=new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(request.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);

    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
