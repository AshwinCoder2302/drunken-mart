package com.drunkenmart.controller;

import com.drunkenmart.entity.Category;
import com.drunkenmart.entity.Product;
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
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{categoryId}")
    public List<Product> getAllProductByCategoryId(@PathVariable(value = "categoryId") String categoryId){
        return categoryService.getAllProductByCategoryId(categoryId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveCategory(@RequestPart(value = "categoryImage") MultipartFile categoryImage) throws IOException {
        return categoryService.saveCategory(categoryImage);
    }

}
