package ru.skillsad.sad.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.views.View;
import ru.skillsad.sad.service.ProdService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProdService prodService;

    public ProductController(ProdService prodService) {
        this.prodService = prodService;
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.IdAndNameAndImg.class)
    public Product getProductById(@PathVariable String id) {
        return prodService.getById(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Product productFromDb = prodService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + productFromDb.getImgName() + "\"")
                .body(productFromDb.getImg());
    }
}
