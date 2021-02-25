package ru.skillsad.sad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.repository.CategoryRepo;

import javax.validation.Valid;
import java.util.List;

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
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }
}
