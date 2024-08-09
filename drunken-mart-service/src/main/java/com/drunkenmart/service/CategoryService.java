package com.drunkenmart.service;

import com.drunkenmart.entity.Category;
import com.drunkenmart.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    String saveCategory(MultipartFile categoryImage) throws IOException;

    List<Product> getAllProductByCategoryId(String categoryId);

    Category getCategoryById(String categoryId);
}
