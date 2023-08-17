package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.CartItemException;
import com.IshuVastralay.EcommerceShop.exception.UserException;
import com.IshuVastralay.EcommerceShop.model.Cart;
import com.IshuVastralay.EcommerceShop.model.CartItem;
import com.IshuVastralay.EcommerceShop.model.Product;
import com.IshuVastralay.EcommerceShop.model.User;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId,Long id,CartItem cartItem)throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    public void removeCartItem (Long userId,Long cartItemId)throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId)throws CartItemException;

}
