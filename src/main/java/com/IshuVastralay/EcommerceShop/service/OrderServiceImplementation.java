package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.OrderException;
import com.IshuVastralay.EcommerceShop.model.Address;
import com.IshuVastralay.EcommerceShop.model.Order;
import com.IshuVastralay.EcommerceShop.model.User;
import com.IshuVastralay.EcommerceShop.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{



    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;
    public OrderServiceImplementation(CartRepository cartRepository, CartService cartService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.productService = productService;
    }
    @Override
    public Order createOrder(User user, Address address) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelledOrder(Long orderId) throws OrderException {
        return null;
    }
}
