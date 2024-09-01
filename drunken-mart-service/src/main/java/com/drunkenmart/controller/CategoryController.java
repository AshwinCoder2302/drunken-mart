package com.drunkenmart.controller;

import com.drunkenmart.dto.CategoryRequestResponseDTO;
import com.drunkenmart.entity.Category;
import com.drunkenmart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryRequestResponseDTO> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveCategory(@RequestPart(value = "categoryImage") MultipartFile categoryImage, @RequestPart(value ="category") Category category) throws IOException {
        return categoryService.saveCategory(categoryImage, category);
    }

    @PostMapping("/bulk")
    public String saveBulkCategory(@RequestBody List<Category> categories) {
        return categoryService.saveBulkCategory(categories);
    }
}
