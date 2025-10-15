package com.products.products.service;

import com.products.products.entities.Category;
import com.products.products.entities.Product;
import com.products.products.repos.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;



    public void createCategory(Category category){


    }

    @Transactional
    public void appendProductCategory(Product product) {
        Category ifExists = categoryRepository.getCategoryByCategoryName(product.getCategory().getCategoryName());
        if(ifExists==null){
            createCategory(product.getCategory());
        }
        else{
            ifExists.getProducts().add(product);
            categoryRepository.save(ifExists);
        }
    }
}
