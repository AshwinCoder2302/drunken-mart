package com.drunkenmart.service;

import com.drunkenmart.dto.CategoryRequestResponseDTO;
import com.drunkenmart.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategoryRequestResponseDTO> getAllCategory();

    String saveCategory(MultipartFile categoryImage, Category category) throws IOException;

    Category getCategoryById(String categoryId);

    String saveBulkCategory(List<Category> categories);
}
