package com.IshuVastralay.EcommerceShop.service;

import com.IshuVastralay.EcommerceShop.exception.OrderException;
import com.IshuVastralay.EcommerceShop.model.Address;
import com.IshuVastralay.EcommerceShop.model.Order;
import com.IshuVastralay.EcommerceShop.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address address);
    public Order findOrderById(Long orderId)throws OrderException;
    public List<Order> userOrderHistory(Long userId);
    public List<Order> getAllOrders();
    public Order placedOrder(Long orderId)throws OrderException;
    public Order confirmedOrder(Long orderId)throws OrderException;
    public Order shippedOrder(Long orderId)throws OrderException;
    public Order deliveredOrder(Long orderId)throws OrderException;
    public Order cancelledOrder(Long orderId)throws OrderException;
    public void deleteOrder(Long orderId)throws OrderException;
}
