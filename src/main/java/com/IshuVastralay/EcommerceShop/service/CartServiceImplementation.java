package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.ProductException;
import com.IshuVastralay.EcommerceShop.model.Cart;
import com.IshuVastralay.EcommerceShop.model.CartItem;
import com.IshuVastralay.EcommerceShop.model.Product;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.repository.CartRepository;
import com.IshuVastralay.EcommerceShop.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {

    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {

        Cart cart=new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        Cart cart=cartRepository.findByUserId(userId);
        Product product=productService.findProductById(request.getProductId());
        CartItem isPresent=cartItemService.isCartItemExist(cart,product,request.getSize(),userId);
        if(isPresent==null){
            CartItem cartItem=new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);
            int price=request.getQuantity()* product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());
            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return "Items Added to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart=cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalDisconutedPrice=0;
        int totalItem=0;
        for(CartItem cartItem:cart.getCartItems()){
            totalPrice+=cartItem.getPrice();
            totalDisconutedPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getQuantity();
        }
        cart.setTotalDiscountedPrice(totalDisconutedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(totalPrice-totalDisconutedPrice);
        return cartRepository.save(cart);
    }
}
