package com.products.products.repos;

import com.products.products.entities.Category;
import com.products.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

//    void appendProductCategory(Product product);

    Category getCategoryByCategoryName(String name);
}
