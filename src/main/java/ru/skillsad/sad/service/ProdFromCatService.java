package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.CategoryRepo;
import ru.skillsad.sad.repository.ProductFromCategoryRepo;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ProdFromCatService {
    private final CategoryRepo categoryRepo;
    private final ProductFromCategoryRepo productFromCategoryRepo;

    public NoImgFormDB getByIdExceptImg(String id) {
        return productFromCategoryRepo.getById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
    }

    public ProductFromCategory getById(String id) {
        return productFromCategoryRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
    }

    @Transactional
    public void deleteProduct(String id) {
        ProductFromCategory product = productFromCategoryRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));

        Category category = product.getCategoryy();
        category.removeProduct(product);
        categoryRepo.save(category);
    }

    @Transactional
    public ResponseEntity<ResponseTemp> createProduct(MultipartFile file, ProductFromCategory product) {
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

    public void editProduct(ProductFromCategory product) {
        ProductFromCategory productFromDB = productFromCategoryRepo.findById(product.getId())
                .orElseThrow(getNoSuchElementExceptionSupplier(product));

        productFromDB.setName(product.getName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setRating(product.getRating());

        Category category = categoryRepo.findById(productFromDB.getCategoryy().getId())
                .orElseThrow(getNoSuchElementExceptionSupplier(product));

        category.addProduct(productFromDB);
        categoryRepo.save(category);
    }

    private Supplier<NoSuchElementException> getNoSuchElementExceptionSupplier(ProductFromCategory product) {
        return () -> new NoSuchElementException(
                String.format("Не верно указан ид = %s, при редактировании товара %s",
                        product.getId(),
                        product.getName()));
    }

    private Supplier<NoSuchElementException> getNoSuchElementExceptionSupplier(String id) {
        return () -> new NoSuchElementException("Не верно указан ИД тоавра из категории" + id);
    }
}
