package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.domain.views.NoImgFormDB;

import java.util.Optional;

public interface ProductFromCategoryRepo extends JpaRepository<ProductFromCategory, Long> {
    Optional<NoImgFormDB> getById(Long id);
}
