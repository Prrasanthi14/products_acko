package com.products.products;

import com.products.products.dtos.ProductRequestDTO;
import com.products.products.dtos.ProductResponseDTO;
import com.products.products.entities.Category;
import com.products.products.entities.Product;
import com.products.products.service.CategoryService;
import com.products.products.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products/")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getProduct/{id}")
    ResponseEntity<ProductResponseDTO>getProductById(@PathVariable Long id){
        try{
            Product productExists = productService.getProductById(id);

            if(productExists!=null){
                return ResponseEntity.status(HttpStatus.OK)
                        .body( ProductResponseDTO.builder()
                                .id(productExists.getId())
                                .name(productExists.getName())
                                .price(productExists.getPrice())
                                .category(productExists.getCategory())
                                .message("Success").build());
            }
            else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body( ProductResponseDTO.builder().message("Entity not found").build());
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( ProductResponseDTO.builder().message("Error Occurred").build());
        }
    }

    @PostMapping("/createProduct")
    ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Validated ProductRequestDTO productRequestDTO){
        try{
            Product product = Product.builder()
                    .name(productRequestDTO.getProductName())
                    .price(productRequestDTO.getPrice())
                    .category(productRequestDTO.getCategory())
                    .build();

            Category category = product.getCategory();
            ProductResponseDTO productResponseDTO = productService.createProduct(product);
            categoryService.appendProductCategory(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
        }
        catch (Exception e) {
            log.error("Error creating product", e);
            ProductResponseDTO errorResponse = ProductResponseDTO.builder()
                    .name(productRequestDTO.getProductName())
                    .price(productRequestDTO.getPrice())
                    .category(productRequestDTO.getCategory())
                    .message("Failed to create product")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
