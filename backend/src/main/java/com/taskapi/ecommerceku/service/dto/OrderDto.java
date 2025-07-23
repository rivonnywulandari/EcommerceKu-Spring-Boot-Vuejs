package com.taskapi.ecommerceku.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderDto {

    @JsonProperty(value="order_id")
    private String orderId;
    @JsonProperty(value="customer_name")
    private String customerName;
    private List<OrderItemDto> items;
   

    
}
