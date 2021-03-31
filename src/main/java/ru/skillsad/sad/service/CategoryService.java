package ru.skillsad.sad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.views.CategoryExceptImg;
import ru.skillsad.sad.repository.catalog.CategoryRepo;

import java.util.List;

@Service
public class CategoryService extends AbstractServiceForTopEntity<Category, CategoryRepo> {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        super(categoryRepo, Category::new);
        this.categoryRepo = categoryRepo;
    }

    @Transactional(readOnly = true)
    public List<CategoryExceptImg> getAllCategory() {
        return categoryRepo.getAllByIdIsNotNull();
    }
}
