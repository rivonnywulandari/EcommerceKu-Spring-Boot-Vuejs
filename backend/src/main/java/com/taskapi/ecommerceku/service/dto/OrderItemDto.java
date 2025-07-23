package com.taskapi.ecommerceku.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderItemDto {

   
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

