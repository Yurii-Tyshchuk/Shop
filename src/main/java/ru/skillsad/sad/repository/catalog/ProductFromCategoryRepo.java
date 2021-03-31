package ru.skillsad.sad.repository.catalog;

import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.repository.CommonAbstractRepository;

import java.util.Optional;

@Repository
public interface ProductFromCategoryRepo extends CommonAbstractRepository<ProductFromCategory> {
    Optional<NoImgFormDB> getById(Long id);
}
