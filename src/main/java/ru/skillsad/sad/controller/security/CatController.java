package ru.skillsad.sad.controller.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.service.CategoryService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/security")
public class CatController {
    private final CategoryService categoryService;

    public CatController(CategoryService categService) {
        this.categoryService = categService;
    }

    @PostMapping(value = "/editCategory")
    public ResponseEntity<ResponseTemp> editCategory(@Valid @RequestBody Category category) {
        categoryService.editCategory(category);
        log.warn("Была отредактирована категория");
        return new ResponseEntity<>(new ResponseTemp("Данные изменены"), HttpStatus.OK);
    }

    @GetMapping(value = "/deleteCategory/{id}")
    public ResponseEntity<ResponseTemp> deleteCategory(@Valid @PathVariable String id) {
        categoryService.deleteCategory(id);
        log.warn("Была удалена категория");
        return new ResponseEntity<>(new ResponseTemp("Данные удалены " + id), HttpStatus.OK);
    }

    @PostMapping(value = "/createCategory")
    public ResponseEntity<ResponseTemp> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        log.warn("Была создана категория");
        return new ResponseEntity<>(new ResponseTemp("Категория создана"), HttpStatus.OK);
    }
}
