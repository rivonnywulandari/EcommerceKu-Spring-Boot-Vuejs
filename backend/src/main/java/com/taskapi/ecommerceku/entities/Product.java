package com.taskapi.ecommerceku.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product_tbl")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    @Column(length = 100)
    private String name;
   
    private int price;

    @Column(length = 20)
    private String type;

    @Column(name="is_delete", length = 100)
    private int isDelete;

    
}
