package ru.skillsad.sad.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.skillsad.sad.domain.views.CategoryExceptImg;
import ru.skillsad.sad.service.CategoryService;

@RestController
@RequestMapping("/api")
@Transactional
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
//    @JsonView(View.IdAndName.class)
    public List<CategoryExceptImg> category() {
        return categoryService.getAllCategory();
    }
}
