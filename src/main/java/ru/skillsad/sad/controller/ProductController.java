package ru.skillsad.sad.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.service.ProdFromCatService;
import ru.skillsad.sad.service.ProdService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProdService prodService;
    private final ProdFromCatService prodFromCatService;

    public ProductController(ProdService prodService, ProdFromCatService prodFromCatService) {
        this.prodService = prodService;
        this.prodFromCatService = prodFromCatService;
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoImgFormDB getProductById(@PathVariable String id) {
        return prodService.getByIdExceptImg(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Product productFromDb = prodService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + productFromDb.getImgName() + "\"")
                .body(productFromDb.getImg());
    }

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoImgFormDB getProductFromCategoryById(@PathVariable String id){
        return prodFromCatService.getByIdExceptImg(id);
    }

    @GetMapping("/downloads/{id}")
    public ResponseEntity<byte[]> getFileAsProductFromCategory(@PathVariable String id) {
        ProductFromCategory productFromDb = prodFromCatService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + productFromDb.getImgName() + "\"")
                .body(productFromDb.getImg());
    }
}
