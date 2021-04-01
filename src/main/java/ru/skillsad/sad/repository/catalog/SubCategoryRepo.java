package ru.skillsad.sad.repository.catalog;

import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.CommonRepository;
@Repository
public interface SubCategoryRepo extends CommonRepository<SubCategory> {
    SubCategory getById(Long id);
}
