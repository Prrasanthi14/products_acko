package com.products.products.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Table(name ="category")
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name ="category_id")
    private Long categoryId;

    @Column(name ="category_name",nullable = false)
    private String categoryName;

    @Column(name="products")
    @OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Product> products = new ArrayList<>();

}
