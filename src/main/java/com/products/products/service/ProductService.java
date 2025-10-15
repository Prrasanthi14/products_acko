package com.products.products.service;

import com.products.products.dtos.ProductResponseDTO;
import com.products.products.entities.Product;
import com.products.products.repos.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Long id){
            Optional<Product> optionalProduct = productRepository.findById(id);
            if(optionalProduct.isPresent()){
                return optionalProduct.get();
            }
            else{
                return null;
            }
    }

    public ProductResponseDTO createProduct(Product product) {
        try {
            Product exists = productRepository.findByName(product.getName());
            if (exists != null) {
                // Product already exists
                return ProductResponseDTO.builder()
                        .id(exists.getId())
                        .name(exists.getName())
                        .category(exists.getCategory())
                        .price(exists.getPrice())
                        .message("Product already exists in DB")
                        .build();
            } else {
                // Save new product
                Product savedProduct = productRepository.save(product);
                return ProductResponseDTO.builder()
                        .id(savedProduct.getId())
                        .name(savedProduct.getName())
                        .category(savedProduct.getCategory())
                        .price(savedProduct.getPrice())
                        .message("Product created successfully")
                        .build();
            }
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new RuntimeException("Could not create product");
        }
    }
}
