package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.CategoryRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

@Service
@AllArgsConstructor
public class SubCategService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

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
