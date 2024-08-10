package com.drunkenmart.service.impl;

import com.drunkenmart.dto.CategoryBulkDTO;
import com.drunkenmart.entity.Category;
import com.drunkenmart.entity.Product;
import com.drunkenmart.repository.CategoryRepository;
import com.drunkenmart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public String saveBulkCategory(List<CategoryBulkDTO> categoryBulkDTOs) {
       List<Category> categoryList = categoryBulkDTOs.stream().map(categoryDTO -> {
            Category category = new Category();
            try {
                File file = new File(categoryDTO.getImagePath());

                // Read the file content as a byte array
                byte[] fileContent = Files.readAllBytes(file.toPath());

                // Encode the byte array to a Base64 string
                String encodedImage = Base64.getEncoder().encodeToString(fileContent);

                // Create a new Category object and set the fields
                category.setImage(encodedImage);
                category.setName(categoryDTO.getName());

            } catch (IOException e) {
                // Handle the exception (e.g., log it, return an error response, etc.)
                e.printStackTrace();
                throw new RuntimeException("Failed to read file: " + categoryDTO.getImagePath(), e);
            }
            return category;
        }).toList();

        // Save the category list
        categoryRepository.saveAll(categoryList);
        return "Bulk Category Saved Successfully";
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
