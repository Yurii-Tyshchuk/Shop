package ru.skillsad.sad.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.domain.views.View;
import ru.skillsad.sad.repository.CategoryRepo;
import ru.skillsad.sad.repository.ProductRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogController {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    public CatalogController(CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @GetMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.IdAndName.class)
    public List<Category> category() throws IOException {
        return categoryRepo.findAll();
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Full.class)
    public Product getProductById(@PathVariable String id) {
        return productRepo.findById(Long.valueOf(id)).get();
    }
}
