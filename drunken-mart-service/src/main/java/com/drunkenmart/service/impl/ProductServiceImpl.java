package com.drunkenmart.service.impl;

import com.drunkenmart.dto.ProductBulkDTO;
import com.drunkenmart.entity.Category;
import com.drunkenmart.entity.Product;
import com.drunkenmart.repository.ProductRepository;
import com.drunkenmart.service.CategoryService;
import com.drunkenmart.service.ProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public String saveBulkProduct(String categoryId, List<ProductBulkDTO> productBulkDTO) {
        Category category = categoryService.getCategoryById(categoryId);
        if(!ObjectUtils.isEmpty(category)){
           List<Product> products = productBulkDTO.stream().map(productDTO -> {
                Product product = new Product();
                try {
                    File file = new File(productDTO.getImagePath());

                    // Read the file content as a byte array
                    byte[] fileContent = Files.readAllBytes(file.toPath());

                    // Encode the byte array to a Base64 string
                    String encodedImage = Base64.getEncoder().encodeToString(fileContent);

                    product.setCategory(category);
                    product.setPrice(productDTO.getPrice());
                    product.setRating(productDTO.getRating());
                    product.setImage(encodedImage);
                    product.setName(productDTO.getName());
                    product.setDetails(productDTO.getDetails());
                    product.setReview(productDTO.getReview());
                    product.setBrandName(productDTO.getBrandName());
                    product.setDiscount(productDTO.getDiscount());
                } catch (IOException e) {
                    // Handle the exception (e.g., log it, return an error response, etc.)
                    e.printStackTrace();
                    throw new RuntimeException("Failed to read file: " + productDTO.getImagePath(), e);
                }
                return product;
            }).toList();
            productRepository.saveAll(products);
        }
        return "Bulk Product Saved Successfully";
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
