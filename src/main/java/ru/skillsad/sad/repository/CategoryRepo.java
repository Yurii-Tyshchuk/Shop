package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.Category;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category getById(Long id);
    Category findByName(String name);

//    @EntityGraph(attributePaths = "test",type = EntityGraph.EntityGraphType.LOAD)
//    Category test2();
//
//    @Query(value="SELECT s.id,s.name, array_agg(d), array_agg(f) FROM category s,sub_category d,product f GROUP BY s.id",nativeQuery = true)
//    List<Category> test();
}
