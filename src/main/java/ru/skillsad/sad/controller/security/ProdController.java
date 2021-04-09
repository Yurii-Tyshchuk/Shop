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
import ru.skillsad.sad.repository.ProductFromCategoryRepo;
import ru.skillsad.sad.repository.ProductRepo;
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
    public ResponseEntity<ResponseTemp> upload(@RequestPart("file") MultipartFile file,
                                               @ModelAttribute Product product) {
        return prodService.createProduct(file,product);
    }

    @PostMapping(
            value = "/uploads",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            headers = "Content-Type= multipart/form-data")
    public ResponseEntity<ResponseTemp> uploadToCategory(@RequestPart("file") MultipartFile file,
                                                         @ModelAttribute ProductFromCategory product) {
        return prodFromCatService.createProduct(file,product);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<ResponseTemp> deleteProduct(@PathVariable String id) {
        prodService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseTemp("Товар удален"), HttpStatus.OK);
    }

    @GetMapping("/deletes/{id}")
    public ResponseEntity<ResponseTemp> deleteProductFromCategory(@PathVariable String id) {
        prodFromCatService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseTemp("Товар удален"), HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<ResponseTemp> editProduct(@RequestBody Product product){
        prodService.editProduct(product);
        return new ResponseEntity<>(new ResponseTemp("Товар изменен"), HttpStatus.OK);
    }

    @PostMapping("/editt")
    public ResponseEntity<ResponseTemp> editProduct(@RequestBody ProductFromCategory product){
        prodFromCatService.editProduct(product);
        return new ResponseEntity<>(new ResponseTemp("Товар изменен"), HttpStatus.OK);
    }
}
