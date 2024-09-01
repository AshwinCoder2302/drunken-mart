package com.drunkenmart.service.impl;

import com.drunkenmart.dto.CategoryRequestResponseDTO;
import com.drunkenmart.entity.Category;
import com.drunkenmart.repository.CategoryRepository;
import com.drunkenmart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
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
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryRequestResponseDTO> getAllCategory()  {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryRequestResponseDTO> categoryRequestResponseDTOList = categories.stream().map(category -> {
            CategoryRequestResponseDTO categoryRequestResponseDTO = new CategoryRequestResponseDTO();
            try {
                categoryRequestResponseDTO.setId(category.getId());
                categoryRequestResponseDTO.setImage(imageToByteArray(category.getImagePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            categoryRequestResponseDTO.setName(category.getName());
            return categoryRequestResponseDTO;
        }).toList();
        return categoryRequestResponseDTOList;
    }

    private String imageToByteArray(String imageURL) throws IOException {
        File file = new File(imageURL);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElse(null);
    }

    @Override
    public String saveBulkCategory(List<Category> categories) {
       List<Category> categoryList = categories.stream().map(category -> {
           try {
               String categoryFileName = Paths.get(category.getImagePath()).getFileName().toString();
               File file = new File(category.getImagePath());
               String resourcePath = new FileSystemResource("src/main/resources/application.properties").getURI().toString().replace("application.properties","").replace("file:","");
               category.setImagePath(resourcePath+"images/categories/"+categoryFileName);
               byte[] fileContent = Files.readAllBytes(file.toPath());
               saveCategoryImageInSpecificPath(categoryFileName, fileContent);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read file: " + category.getImagePath(), e);
            }
            return category;
        }).toList();
        categoryRepository.saveAll(categoryList);
        return "Bulk Category Saved Successfully";
    }

    @Override
    public String saveCategory(MultipartFile categoryImage, Category category) throws IOException {
        String categoryFileName = categoryImage.getOriginalFilename();
        category.setImagePath("/home/user/Documents/drunken-mart/drunken-mart-service/src/main/resources/images/categories/"+categoryFileName);
        saveCategoryImageInSpecificPath(categoryFileName, categoryImage.getBytes());
        categoryRepository.save(category);
        return "Category Saved Successfully";
    }

    public void saveCategoryImageInSpecificPath(String categoryFileName, byte[] categoryImageBytes ) throws IOException {
        Path resourcePath = Paths.get("/home/user/Documents/drunken-mart/drunken-mart-service/src/main/resources");
        Path imagePath = Paths.get(resourcePath + "/images");
        Path categoryPath = Paths.get(imagePath + "/categories");
        if (!Files.exists(imagePath)) {
            Files.createDirectories(imagePath);
        }
        if(!Files.exists(categoryPath)){
            Files.createDirectories(categoryPath);
        }
        Path filePath = categoryPath.resolve(categoryFileName);
        Files.write(filePath, categoryImageBytes);
    }

}
