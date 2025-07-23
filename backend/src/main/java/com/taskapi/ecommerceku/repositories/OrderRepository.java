package com.taskapi.ecommerceku.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taskapi.ecommerceku.entities.Order;

public interface OrderRepository extends CrudRepository<Order, String> {

    Optional<Order> findByOrderId(String orderId);


}
