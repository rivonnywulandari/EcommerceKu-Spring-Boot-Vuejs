package com.taskapi.ecommerceku.repositories;

import org.springframework.data.repository.CrudRepository;

import com.taskapi.ecommerceku.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    Product findByName(String name);

}
