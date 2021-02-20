package ru.skillsad.sad.controller.security;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.CategoryRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/security")
public class SubCatController {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    public SubCatController(SubCategoryRepo subCategoryRepo, CategoryRepo categoryRepo) {
        this.subCategoryRepo = subCategoryRepo;
        this.categoryRepo = categoryRepo;
    }

    @PostMapping(value = "/editSubCategory")
    public ResponseEntity<ResponseTemp> editSubCategory(@Valid @RequestBody SubCategory subCategory) {
        SubCategory subCategoryFromDb = subCategoryRepo.getById(subCategory.getId());
        BeanUtils.copyProperties(subCategory, subCategoryFromDb, "id", "products", "category");
        subCategoryRepo.save(subCategoryFromDb);
        return new ResponseEntity<>(new ResponseTemp("Данные изменены"), HttpStatus.OK);
    }

    @GetMapping(value = "/deleteSubCategory/{id}")
    public ResponseEntity<ResponseTemp> deleteSubCategory(@Valid @PathVariable String id) {
        subCategoryRepo.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(new ResponseTemp("Данные удалены " + id), HttpStatus.OK);
    }

    @GetMapping(value = "/createSubCategory")
    public ResponseEntity<ResponseTemp> createSubCategory(@RequestParam("id") String id,
                                                          @RequestParam("name") String name) {
        Category category = categoryRepo.getById(Long.valueOf(id));
        subCategoryRepo.save(new SubCategory(name, category));
        return new ResponseEntity<>(new ResponseTemp("Подкатегория создана"), HttpStatus.OK);
    }
}
