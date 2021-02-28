package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;

public interface ProductFromCategoryRepo extends JpaRepository<ProductFromCategory, Long> {
    ProductFromCategory getById(Long id);
}
