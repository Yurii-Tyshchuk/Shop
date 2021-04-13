package ru.skillsad.sad.controller.security;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.service.SubCategService;

@Transactional
@RestController
@RequestMapping("/security")
public class SubCatController {

    private final SubCategService subCategService;

    public SubCatController(SubCategService subCategService) {
        this.subCategService = subCategService;
    }

    @PostMapping(value = "/editSubCategory")
    public ResponseEntity<ResponseTemp> editSubCategory(@Valid @RequestBody SubCategory subCategory) {
        subCategService.editSubCategory(subCategory);
        return new ResponseEntity<>(new ResponseTemp("Данные изменены"), HttpStatus.OK);
    }

    @GetMapping(value = "/deleteSubCategory/{id}")
    public ResponseEntity<ResponseTemp> deleteSubCategory(@Valid @PathVariable String id) {
        subCategService.deleteSubCategory(id);
        return new ResponseEntity<>(new ResponseTemp("Данные удалены " + id), HttpStatus.OK);
    }

    @GetMapping(value = "/createSubCategory")
    public ResponseEntity<ResponseTemp> createSubCategory(@RequestParam("id") String id,
                                                          @RequestParam("name") String name) {
        subCategService.createSubCategory(id, name);
        return new ResponseEntity<>(new ResponseTemp("Подкатегория создана"), HttpStatus.OK);
    }
}
