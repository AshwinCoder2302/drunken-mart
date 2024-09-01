package com.drunkenmart.controller;

import com.drunkenmart.dto.ProductRequestResponseDto;
import com.drunkenmart.entity.Product;
import com.drunkenmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class productController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/search")
    public List<ProductRequestResponseDto> getAllProductBySearch(@RequestParam("searchKeyword") String searchKeyword){
        return productService.getAllProductBySearch(searchKeyword);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable(value = "productId") String productId){
        return productService.getProductById(productId);
    }

    @PostMapping(value = "/{categoryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveProduct(@RequestPart(value = "productImage") MultipartFile productImage, @RequestPart(value ="product") Product product, @PathVariable("categoryId") String categoryId) throws IOException {
        return productService.saveProduct(productImage, product, categoryId);
    }

    @PostMapping("/bulk/{categoryId}")
    public String saveBulkProduct(@PathVariable("categoryId") String categoryId, @RequestBody List<Product> products) {
        return productService.saveBulkProduct(categoryId, products);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductRequestResponseDto> getAllProductByCategoryId(@PathVariable(value = "categoryId") String categoryId){
        return productService.getAllProductByCategoryId(categoryId);
    }

}
