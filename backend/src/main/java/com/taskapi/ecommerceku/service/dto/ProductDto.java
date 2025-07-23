package com.taskapi.ecommerceku.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id; // ✅ Tambahkan ini

    private String name;
    private int price;
    private String type;

    @JsonProperty(value = "is_delete")
    private int isDelete;

    public ProductDto() {}

    public ProductDto(Long id, String name, int price, String type, int isDelete) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.isDelete = isDelete;
    }
}
