package ru.skillsad.sad.repository.catalog;

import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.repository.CommonRepository;
import ru.skillsad.sad.repository.CustomQueryProduct;

import java.util.Optional;

@Repository
public interface ProductFromCategoryRepo extends CommonRepository<ProductFromCategory>, CustomQueryProduct {
}
