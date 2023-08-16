package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Cart;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest request)throws ProductException;
    public Cart findUserCart(Long userId);



}
