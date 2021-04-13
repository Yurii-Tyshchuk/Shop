package ru.skillsad.sad.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.views.CategoryExceptImg;
import ru.skillsad.sad.repository.CategoryRepo;

/**
 * @author Yurii Ty
 */
@Service
@Transactional
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {this.categoryRepo = categoryRepo;}

    @Transactional
    public void editCategory(Category category) {
        Category categoryFromDb = categoryRepo.getById(category.getId());
        categoryFromDb.setName(category.getName());
        categoryRepo.save(categoryFromDb);
    }

    public void deleteCategory(String id) {
        categoryRepo.deleteById(Long.valueOf(id));
    }

    public void createCategory(@Valid Category category) {
        categoryRepo.save(new Category(category.getName()));
    }

    @Transactional(readOnly = true)
    public List<CategoryExceptImg> getAllCategory() {
        return categoryRepo.getAllByIdIsNotNull();
    }
}
