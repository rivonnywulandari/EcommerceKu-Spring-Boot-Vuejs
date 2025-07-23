package com.taskapi.ecommerceku.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taskapi.ecommerceku.entities.Cart;

public interface CartRepository extends CrudRepository<Cart, String>{
    List<Cart> findByCustomerName(String customerName);

}
