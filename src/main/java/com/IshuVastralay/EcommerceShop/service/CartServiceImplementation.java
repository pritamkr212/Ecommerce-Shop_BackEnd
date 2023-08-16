package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Cart;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {
    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        return null;
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
