package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.views.CategoryExceptImg;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category getById(Long id);
    Category findByName(String name);
    //    @EntityGraph(value = "testGraphs",type = EntityGraphType.FETCH)
    List<CategoryExceptImg> getAllByIdIsNotNull();

//    @Query(value="SELECT s.id,s.name, array_agg(d), array_agg(f) FROM category s,sub_category d,product f GROUP BY s.id",nativeQuery = true)
//    List<Category> test();
}
