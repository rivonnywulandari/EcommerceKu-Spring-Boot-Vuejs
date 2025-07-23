package com.taskapi.ecommerceku.webapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskapi.ecommerceku.entities.Cart;
import com.taskapi.ecommerceku.service.CartService;
import com.taskapi.ecommerceku.service.dto.CartDto;

@RestController
@RequestMapping("/api/carts")
public class CartResource {
    @Autowired 
    private CartService cartService;

    @PostMapping
    public Cart addToCart(@RequestBody CartDto dto) {
        return cartService.addToCart(dto);
    }

    @GetMapping("/{customerName}")
    public List<Cart> getCart(@PathVariable String customerName) {
        return cartService.getCartByCustomer(customerName);
    }
}

