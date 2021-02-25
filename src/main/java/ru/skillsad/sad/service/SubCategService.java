package ru.skillsad.sad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.CategoryRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

@Service
public class SubCategService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    public SubCategService(SubCategoryRepo subCategoryRepo, CategoryRepo categoryRepo) {
        this.subCategoryRepo = subCategoryRepo;
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public void editSubCategory(SubCategory subCategory){
        SubCategory subCategoryFromDb = subCategoryRepo.getById(subCategory.getId());
        subCategoryFromDb.setName(subCategory.getName());
        subCategoryRepo.save(subCategoryFromDb);
    }

    public void deleteSubCategory(String id){
        SubCategory subCategory = subCategoryRepo.getById(Long.valueOf(id));
        Category category = subCategory.getCategory();
        category.removeSubCategory(subCategory);
        categoryRepo.save(category);
    }

    @Transactional
    public void createSubCategory(String id,String name){
        Category category = categoryRepo.getById(Long.valueOf(id));
        category.addSubCategory(new SubCategory(name));
        categoryRepo.save(category);
    }
}
