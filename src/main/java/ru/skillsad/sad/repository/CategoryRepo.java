package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.Category;

import java.util.List;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category getById(Long id);
    Category findByName(String name);
}
