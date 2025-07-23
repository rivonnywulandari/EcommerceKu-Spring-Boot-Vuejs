package com.taskapi.ecommerceku.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskapi.ecommerceku.entities.Cart;
import com.taskapi.ecommerceku.repositories.CartRepository;
import com.taskapi.ecommerceku.service.dto.CartDto;

@Service
public class CartService {
    @Autowired 
    private CartRepository cartRepo;

    public Cart addToCart(CartDto dto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(dto, cart);
        return cartRepo.save(cart);
    }

    public List<Cart> getCartByCustomer(String name) {
        return cartRepo.findByCustomerName(name);
    }
}

