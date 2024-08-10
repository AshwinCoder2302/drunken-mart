package com.drunkenmart.service;

import com.drunkenmart.dto.ProductBulkDTO;
import com.drunkenmart.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    String saveProduct(MultipartFile productImage) throws IOException;

    Product getProductById(String productId);

    String saveBulkProduct(String categoryId, List<ProductBulkDTO> productBulkDTO);
}
