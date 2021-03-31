package ru.skillsad.sad.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.views.CategoryExceptImg;
import ru.skillsad.sad.repository.CommonAbstractRepository;

import java.util.List;

@Repository
public interface CategoryRepo extends CommonAbstractRepository<Category> {
    Category getById(Long id);
    //    @EntityGraph(value = "testGraphs",type = EntityGraphType.FETCH)
    List<CategoryExceptImg> getAllByIdIsNotNull();
}
