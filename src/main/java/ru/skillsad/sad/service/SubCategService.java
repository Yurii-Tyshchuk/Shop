package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.catalog.CategoryRepo;
import ru.skillsad.sad.repository.catalog.SubCategoryRepo;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class SubCategService {
    private final CategoryRepo categoryRepo;
    private final SubCategoryRepo subCategoryRepo;

    @Transactional
    public void editSubCategory(SubCategory subCategory) {
        SubCategory subCategoryFromDb = subCategoryRepo.findById(subCategory.getId())
                .orElseThrow(getNoSuchElementExceptionSupplier(subCategory.getId().toString()));
        subCategoryFromDb.setName(subCategory.getName());
        subCategoryRepo.save(subCategoryFromDb);
    }

    public void deleteSubCategory(String id) {
        SubCategory subCategory = subCategoryRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));

        Category category = subCategory.getCategory();
        category.removeSubCategory(subCategory);
        categoryRepo.save(category);
    }

    @Transactional
    public void createSubCategory(String id, String name) {
        Category category = categoryRepo.getById(Long.valueOf(id));
        category.addSubCategory(new SubCategory(name));
        categoryRepo.save(category);
    }

    private Supplier<NoSuchElementException> getNoSuchElementExceptionSupplier(String id) {
        return () -> new NoSuchElementException("Не верно указан ИД товара из категории " + id);
    }
}
