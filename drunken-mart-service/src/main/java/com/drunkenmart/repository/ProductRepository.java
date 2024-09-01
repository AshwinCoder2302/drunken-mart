package com.drunkenmart.repository;

import com.drunkenmart.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByNameStartingWithIgnoreCase(String searchKeyword);

    List<Product> findByCategoryId(String categoryId);
}
