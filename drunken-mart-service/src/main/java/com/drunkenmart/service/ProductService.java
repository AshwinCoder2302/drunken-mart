package com.drunkenmart.service;

import com.drunkenmart.dto.ProductRequestResponseDto;
import com.drunkenmart.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    String saveProduct(MultipartFile productImage, Product product, String categoryId) throws IOException;

    Product getProductById(String productId);

    String saveBulkProduct(String categoryId, List<Product> products);

    List<ProductRequestResponseDto> getAllProductBySearch(String searchKeyword);

    List<ProductRequestResponseDto> getAllProductByCategoryId(String categoryId);
}
