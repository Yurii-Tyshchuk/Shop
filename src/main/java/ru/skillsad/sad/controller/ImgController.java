package ru.skillsad.sad.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.repository.ProductRepo;

@Controller
@RequestMapping("/img")
public class ImgController {
    private final ProductRepo productRepo;

    public ImgController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/download/{id}")
    @Transactional
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Product productFromDb = productRepo.getById(Long.valueOf(id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + productFromDb.getImgName() + "\"")
                .body(productFromDb.getImg());
    }
}
