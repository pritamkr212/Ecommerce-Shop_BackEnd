package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Product;
import com.IshuVastralay.EcommerceShop.model.Rating;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.repository.RatingRepository;
import com.IshuVastralay.EcommerceShop.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService {
    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product=productService.findProductById(request.getProductId());
        Rating newRating=new Rating();
        newRating.setProduct(product);
        newRating.setUser(user);
        newRating.setRating(request.getRating());
        newRating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(newRating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
