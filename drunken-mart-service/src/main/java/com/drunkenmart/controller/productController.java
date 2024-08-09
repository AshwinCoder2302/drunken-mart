package com.drunkenmart.controller;

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
    public List<Product> getAllCategory(){
        return productService.getAllProduct();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable(value = "productId") String productId){
        return productService.getProductById(productId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveProduct(@RequestPart(value = "productImage") MultipartFile productImage) throws IOException {
        return productService.saveProduct(productImage);
    }
}
