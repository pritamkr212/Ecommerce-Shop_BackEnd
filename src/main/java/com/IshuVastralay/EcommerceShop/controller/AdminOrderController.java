package com.IshuVastralay.EcommerceShop.controller;

import com.IshuVastralay.EcommerceShop.authResponse.ApiResponse;
import com.IshuVastralay.EcommerceShop.exception.OrderException;
import com.IshuVastralay.EcommerceShop.model.Order;
import com.IshuVastralay.EcommerceShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/")
    public ResponseEntity<List<Order>>getAllOrdersHandler(){
        List<Order>orderList=orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orderList, HttpStatus.ACCEPTED);
    }
    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order>confirmedOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws OrderException{
        Order order=orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order>shippedOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws OrderException{
        Order order=orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("{orderId}/deliver")
    public ResponseEntity<Order>deliverOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws  OrderException{
        Order order=orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping("{orderId}/cancel")
    public ResponseEntity<Order>cancelOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws  OrderException{
        Order order=orderService.cancelledOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @DeleteMapping("{orderId}/delete")
    public ResponseEntity<ApiResponse>deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt)throws  OrderException{
        orderService.deleteOrder(orderId);
        ApiResponse res=new ApiResponse();
        res.setMessage("order deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
