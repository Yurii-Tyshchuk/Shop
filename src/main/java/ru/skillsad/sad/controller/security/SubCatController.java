package ru.skillsad.sad.controller.security;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.SubCategoryRepo;

import javax.validation.Valid;

@RestController
@RequestMapping("/security")
public class SubCatController {
    private final SubCategoryRepo subCategoryRepo;

    public SubCatController(SubCategoryRepo subCategoryRepo) {
        this.subCategoryRepo = subCategoryRepo;
    }

    @PostMapping(value = "/editSubCategory")
    public ResponseEntity<ResponseTemp> editSubCategory(@Valid @RequestBody SubCategory subCategory) {
        SubCategory subCategoryFromDb = subCategoryRepo.getById(subCategory.getId());
        BeanUtils.copyProperties(subCategory, subCategoryFromDb, "id", "products","category");
        subCategoryRepo.save(subCategoryFromDb);
        return new ResponseEntity<>(new ResponseTemp("Данные изменены"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteSubCategory/{id}")
    public ResponseEntity<ResponseTemp> deleteSubCategory(@Valid @PathVariable String id) {
        subCategoryRepo.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(new ResponseTemp("Данные удалены " + id), HttpStatus.OK);
    }

    @PostMapping(value = "/createSubCategory")
    public ResponseEntity<ResponseTemp> createSubCategory(@Valid @RequestParam SubCategory category) {
        subCategoryRepo.save(category);
        return new ResponseEntity<>(new ResponseTemp("Категория создана"), HttpStatus.OK);
    }
}
