package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Review;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest request, User user)throws ProductException;
    public List<Review>getAllReview(Long productId);
}
