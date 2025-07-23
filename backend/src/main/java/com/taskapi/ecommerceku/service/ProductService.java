package com.taskapi.ecommerceku.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskapi.ecommerceku.entities.Product;
import com.taskapi.ecommerceku.repositories.ProductRepository;
import com.taskapi.ecommerceku.service.dto.ProductDto;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    public ProductDto getByName(String name){
        Product product = productRepository.findByName(name);
        ProductDto productDto = mapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> getAll(){
        List<ProductDto> productDtos = new ArrayList<>();
        Iterable<Product> iterableProduct = productRepository.findAll();
        for(Product product :  iterableProduct){
            ProductDto dto = mapper.map(product, ProductDto.class);
            productDtos.add(dto);
        }
        return productDtos;
    }

    public void save(ProductDto productDto){
        Product product = mapper.map(productDto, Product.class);
       
        productRepository.save(product);
    }

    public ProductDto softDelete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setIsDelete(2); 
        productRepository.save(product);

        return mapper.map(product, ProductDto.class);
    }

    public ProductDto update(Long id, ProductDto updatedDto) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(updatedDto.getName());
        existingProduct.setPrice(updatedDto.getPrice());
        existingProduct.setType(updatedDto.getType());
        // Optional: update isDelete kalau ingin
        // existingProduct.setIsDelete(updatedDto.getIsDelete());

        Product savedProduct = productRepository.save(existingProduct);
        return mapper.map(savedProduct, ProductDto.class);
    }



}
