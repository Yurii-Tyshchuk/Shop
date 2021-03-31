package ru.skillsad.sad.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.catalog.ProductFromCategoryRepo;
import ru.skillsad.sad.repository.catalog.ProductRepo;
import ru.skillsad.sad.service.ProdFromCatService;
import ru.skillsad.sad.service.ProdService;

import java.io.IOException;

@Controller
@RequestMapping("/security")
public class ProdController {
    private final ProductRepo productRepo;
    private final ProductFromCategoryRepo productFromCategoryRepo;
    private final ProdService prodService;
    private final ProdFromCatService prodFromCatService;

    public ProdController(ProductRepo productRepo, ProductFromCategoryRepo productFromCategoryRepo, ProdService prodService, ProdFromCatService prodFromCatService) {
        this.productRepo = productRepo;
        this.productFromCategoryRepo = productFromCategoryRepo;
        this.prodService = prodService;
        this.prodFromCatService = prodFromCatService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            headers = "Content-Type= multipart/form-data")
    @Transactional
    public ResponseEntity<ResponseTemp> upload(@RequestPart("file") MultipartFile file,
                                               @ModelAttribute Product product) {
        try {
            product.setImg(file.getBytes());
            productRepo.save(product);
            return new ResponseEntity<>(new ResponseTemp("Картинка загружена"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(
                    new ResponseTemp("Не удалось загрузить => " + file.getOriginalFilename()),
                    HttpStatus.CONFLICT);
        }
    }

    @PostMapping(
            value = "/uploads",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            headers = "Content-Type= multipart/form-data")
    @Transactional
    public ResponseEntity<ResponseTemp> uploadToCategory(@RequestPart("file") MultipartFile file,
                                                         @ModelAttribute ProductFromCategory product) {
        try {
            product.setImg(file.getBytes());
            productFromCategoryRepo.save(product);
            return new ResponseEntity<>(new ResponseTemp("Картинка загружена"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(
                    new ResponseTemp("Не удалось загрузить => " + file.getOriginalFilename()),
                    HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<ResponseTemp> deleteProduct(@PathVariable String id) {
        prodService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseTemp("Товар удален"), HttpStatus.OK);
    }

    @GetMapping("/deletes/{id}")
    @Transactional
    public ResponseEntity<ResponseTemp> deleteProductFromCategory(@PathVariable String id) {
        prodFromCatService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseTemp("Товар удален"), HttpStatus.OK);
    }
}
