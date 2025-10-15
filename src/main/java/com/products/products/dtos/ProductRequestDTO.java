package com.products.products.dtos;

import com.products.products.entities.Category;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    @NonNull
    private String productName;
    @NonNull
    private Double price;
    @NonNull
    private Category category;

}
