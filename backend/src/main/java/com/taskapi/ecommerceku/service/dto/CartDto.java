package com.taskapi.ecommerceku.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartDto {
    @JsonProperty(value="customer_name")
    private String customerName;

    @JsonProperty(value="product_id")
    private Long productId;

    @JsonProperty(value="product_name")
    private String productName;

    @JsonProperty(value="product_price")
    private int productPrice;
    
    private int quantity;

    @JsonProperty(value="product_type")
    private String productType;
}

