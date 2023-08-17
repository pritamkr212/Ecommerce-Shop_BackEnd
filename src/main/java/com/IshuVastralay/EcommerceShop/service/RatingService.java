package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Rating;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.request.RatingRequest;

import java.util.List;


public interface RatingService {
    public Rating createRating(RatingRequest request, User user)throws ProductException;
    public List<Rating> getProductsRating(Long productId);
}
