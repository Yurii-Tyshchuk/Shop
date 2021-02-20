package ru.skillsad.sad.controller.security;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.CategoryRepo;

import javax.validation.Valid;

@RestController
@RequestMapping("/security")
public class CatController {
    private final CategoryRepo categoryRepo;

    public CatController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @PostMapping(value = "/editCategory")
    @Transactional
    public ResponseEntity<ResponseTemp> editCategory(@Valid @RequestBody Category category) {
        Category categoryFromDb = categoryRepo.getById(category.getId());
        BeanUtils.copyProperties(category, categoryFromDb, "id", "subCategoryList");
        categoryRepo.save(categoryFromDb);
        return new ResponseEntity<>(new ResponseTemp("Данные изменены"), HttpStatus.OK);
    }

    @GetMapping(value = "/deleteCategory/{id}")
    public ResponseEntity<ResponseTemp> deleteCategory(@Valid @PathVariable String id) {
        categoryRepo.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(new ResponseTemp("Данные удалены " + id), HttpStatus.OK);
    }

    @PostMapping(value = "/createCategory")
    public ResponseEntity<ResponseTemp> createCategory(@Valid @RequestBody Category category) {
        categoryRepo.save(new Category(category.getName()));
        return new ResponseEntity<>(new ResponseTemp("Категория создана"), HttpStatus.OK);
    }
}
