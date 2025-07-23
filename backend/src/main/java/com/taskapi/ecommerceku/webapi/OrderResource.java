package com.taskapi.ecommerceku.webapi;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskapi.ecommerceku.entities.Cart;
import com.taskapi.ecommerceku.entities.Order;
import com.taskapi.ecommerceku.service.CartService;
import com.taskapi.ecommerceku.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {

    private final ModelMapper modelMapper;
    
    @Autowired 
    private OrderService orderService;
    @Autowired 
    private CartService cartService;

    OrderResource(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{customerName}")
    public Order placeOrder(@PathVariable String customerName) {
        List<Cart> cartItems = cartService.getCartByCustomer(customerName);
        return orderService.placeOrder(customerName, cartItems);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable String orderId) {
        return orderService.getOrderByOrderId(orderId)
            .map(order -> ResponseEntity.ok(modelMapper.map(order, Order.class)))
            .orElse(ResponseEntity.notFound().build());
    }


}

