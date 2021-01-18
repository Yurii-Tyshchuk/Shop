package ru.skillsad.sad.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.ProductRepo;

import java.io.IOException;

@Controller
@RequestMapping("/security")
public class ImgSecController {
    private final ProductRepo productRepo;

    public ImgSecController(ProductRepo productRepo) {this.productRepo = productRepo;}

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = "Content-Type= multipart/form-data")
    public ResponseEntity<ResponseTemp> upload(@RequestPart("file") MultipartFile file, @ModelAttribute Product product) {
        try {
            product.setImg(file.getBytes());
            productRepo.save(product);
            return new ResponseEntity<>(new ResponseTemp("Картинка загружена"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseTemp("Не удалось загрузить => " + file.getOriginalFilename()), HttpStatus.CONFLICT);
        }
    }
}
