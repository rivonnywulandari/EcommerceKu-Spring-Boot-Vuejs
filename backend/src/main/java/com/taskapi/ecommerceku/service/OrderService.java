package com.taskapi.ecommerceku.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskapi.ecommerceku.entities.Cart;
import com.taskapi.ecommerceku.entities.Order;
import com.taskapi.ecommerceku.entities.OrderItem;
import com.taskapi.ecommerceku.repositories.OrderRepository;

@Service
public class OrderService {

    private final ModelMapper modelMapper;

    @Autowired 
    private OrderRepository orderRepository;

    OrderService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Order placeOrder(String customerName, List<Cart> cartItems) {
        Order order = new Order();
        order.setOrderId(generateOrderId());
        order.setCustomerName(customerName);

        List<OrderItem> items = cartItems.stream().map(c -> {
            OrderItem item = new OrderItem();
            item.setProductId(c.getProductId());
            item.setProductName(c.getProductName());
            item.setProductPrice(c.getProductPrice());
            item.setQuantity(c.getQuantity());
            item.setProductType(c.getProductType());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        return orderRepository.save(order);
    }

    private String generateOrderId() {
        return "D" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }


    public Optional<Order> getOrderByOrderId(String orderId) {
    return orderRepository.findByOrderId(orderId)
            .map(order -> modelMapper.map(order, Order.class)); 
}



}

