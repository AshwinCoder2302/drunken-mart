package com.drunkenmart.service.impl;

import com.drunkenmart.entity.Category;
import com.drunkenmart.entity.Product;
import com.drunkenmart.repository.ProductRepository;
import com.drunkenmart.service.CategoryService;
import com.drunkenmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public String saveProduct(MultipartFile productImage) throws IOException {
        Product product = new Product();
        product.setName("White Shirt");
        product.setPrice(550);
        product.setRating(3);
        product.setImage(Base64.getEncoder().encodeToString(productImage.getBytes()));
        Category category = categoryService.getCategoryById("37c4a408-c70e-45da-a750-762d324954b0");
        product.setCategory(category);
        productRepository.save(product);
        return "Product Saved Successfully";
    }

}
