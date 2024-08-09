package com.drunkenmart.service.impl;

import com.drunkenmart.entity.Category;
import com.drunkenmart.entity.Product;
import com.drunkenmart.repository.CategoryRepository;
import com.drunkenmart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductByCategoryId(String categoryId) {
        return categoryRepository.findById(categoryId).get().getProducts();
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public String saveCategory(MultipartFile categoryImage) throws IOException {
        Category category = new Category();
        category.setName("Category");
        category.setImage(Base64.getEncoder().encodeToString(categoryImage.getBytes()));
        categoryRepository.save(category);
        return "Category Saved Successfully";
    }
}
