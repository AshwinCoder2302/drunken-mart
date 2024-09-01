package com.drunkenmart.dto;

import lombok.Data;

@Data
public class ProductRequestResponseDto {
    private String id;
    private String brandName;
    private String name;
    private Integer rating;
    private Integer review;
    private Integer discount;
    private Integer price;
    private String details;
    private String image;
}
