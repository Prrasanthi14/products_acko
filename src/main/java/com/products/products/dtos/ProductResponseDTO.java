package com.products.products.dtos;

import com.products.products.entities.Category;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ProductResponseDTO {


    @NonNull
    Long id;
    @NonNull
    String name;

    private Double price;
    Category category;
    String message;

}
