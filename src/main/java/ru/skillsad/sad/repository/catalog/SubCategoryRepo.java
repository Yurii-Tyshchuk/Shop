package ru.skillsad.sad.repository.catalog;

import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.CommonAbstractRepository;
@Repository
public interface SubCategoryRepo extends CommonAbstractRepository<SubCategory> {
    SubCategory getById(Long id);
}
