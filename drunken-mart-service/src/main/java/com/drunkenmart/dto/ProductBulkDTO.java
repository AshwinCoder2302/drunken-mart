package com.drunkenmart.dto;

import lombok.Data;

@Data
public class ProductBulkDTO {
    private String brandName;
    private String name;
    private Integer rating;
    private Integer review;
    private Integer discount;
    private Integer price;
    private String details;
    private String imagePath;
}
