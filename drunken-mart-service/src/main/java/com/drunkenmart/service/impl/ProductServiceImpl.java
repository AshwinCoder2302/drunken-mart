package com.drunkenmart.service.impl;

import com.drunkenmart.dto.ProductRequestResponseDto;
import com.drunkenmart.entity.Category;
import com.drunkenmart.entity.Product;
import com.drunkenmart.repository.ProductRepository;
import com.drunkenmart.service.CategoryService;
import com.drunkenmart.service.ProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public Product getProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    @Override
    public String saveBulkProduct(String categoryId, List<Product> products) {
        Category category = categoryService.getCategoryById(categoryId);
        if(!ObjectUtils.isEmpty(category)){
           List<Product> productList = products.stream().map(product -> {
                try {
                    String productFileName = Paths.get(product.getImagePath()).getFileName().toString();
                    File file = new File(product.getImagePath());
                    byte[] fileContent = Files.readAllBytes(file.toPath());
                    product.setCategory(category);
                    String resourcePath = new FileSystemResource("src/main/resources/application.properties").getURI().toString().replace("application.properties","").replace("file:","");
                    product.setImagePath(resourcePath+"images/products/"+category.getName()+"/"+productFileName);
                    saveProductImageInSpecificPath(productFileName, category.getName(), fileContent);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read file: " + product.getImagePath(), e);
                }
                return product;
            }).toList();
            productRepository.saveAll(productList);
        }
        return "Bulk Product Saved Successfully";
    }

    @Override
    public List<ProductRequestResponseDto> getAllProductBySearch(String searchKeyword) {
        List<Product> products = productRepository.findByNameStartingWithIgnoreCase(searchKeyword);
        return transformProductToProductResponseDto(products);
    }

    public List<ProductRequestResponseDto> transformProductToProductResponseDto(List<Product> products) {
        List<ProductRequestResponseDto> productRequestResponseDtoList = products.stream().map(product -> {
            ProductRequestResponseDto productRequestResponseDto = new ProductRequestResponseDto();
            try {
                productRequestResponseDto.setId(product.getId());
                productRequestResponseDto.setImage(imageToByteArray(product.getImagePath()));
                productRequestResponseDto.setName(product.getName());
                productRequestResponseDto.setBrandName(product.getBrandName());
                productRequestResponseDto.setDetails(product.getDetails());
                productRequestResponseDto.setPrice(product.getPrice());
                productRequestResponseDto.setDiscount(product.getDiscount());
                productRequestResponseDto.setRating(product.getRating());
                productRequestResponseDto.setReview(product.getReview());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return productRequestResponseDto;

        }).toList();
        return productRequestResponseDtoList;
    }

    @Override
    public List<ProductRequestResponseDto> getAllProductByCategoryId(String categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return transformProductToProductResponseDto(products);
    }

    private String imageToByteArray(String imageURL) throws IOException {
        File file = new File(imageURL);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    @Override
    public String saveProduct(MultipartFile productImage, Product product, String categoryId) throws IOException {
        String productFileName = productImage.getOriginalFilename();
        Category category = categoryService.getCategoryById(categoryId);
        String categoryName = category.getName();
        product.setImagePath("/home/user/Documents/drunken-mart/drunken-mart-service/src/main/resources/images/products/"+categoryName+"/"+productFileName);
        product.setCategory(category);
        saveProductImageInSpecificPath(productFileName, categoryName, productImage.getBytes());
        productRepository.save(product);
        return "Product Saved Successfully";
    }

    public void saveProductImageInSpecificPath(String productFileName, String categoryName, byte[] productImageBytes) throws IOException {
        Path resourcePath = Paths.get("/home/user/Documents/drunken-mart/drunken-mart-service/src/main/resources");
        Path imagePath = Paths.get(resourcePath + "/images");
        Path productPath = Paths.get(imagePath + "/products");
        Path categoryPath = Paths.get(productPath +"/"+ categoryName);
        if (!Files.exists(imagePath)) {
            Files.createDirectories(imagePath);
        }
        if(!Files.exists(productPath)){
            Files.createDirectories(productPath);
        }
        if(!Files.exists(categoryPath)){
            Files.createDirectories(categoryPath);
        }
        Path filePath = categoryPath.resolve(productFileName);
        Files.write(filePath, productImageBytes);
    }

}
