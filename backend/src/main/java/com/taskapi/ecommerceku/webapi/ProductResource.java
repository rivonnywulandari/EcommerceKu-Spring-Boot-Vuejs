package com.taskapi.ecommerceku.webapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.taskapi.ecommerceku.service.ProductService;
import com.taskapi.ecommerceku.service.dto.ProductDto;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getUsers(){
        return productService.getAll();
    }

    @GetMapping("/{name}")
    public ProductDto getProduct(@PathVariable String name){
        return productService.getByName(name);
    }

    @PostMapping
    public ProductDto newProduct(@RequestBody ProductDto product){
        productService.save(product);
        return product;
    }

   
    // @DeleteMapping("/{id}")
    // public ProductDto softDeleteProduct(@PathVariable Long id) {
    //     return productService.softDelete(id);
    // }

    @DeleteMapping("/{id}/delete")
    public ProductDto softDeleteProduct(@PathVariable Long id) {
        return productService.softDelete(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }




}
