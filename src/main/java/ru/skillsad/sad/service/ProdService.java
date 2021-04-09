package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.ProductRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ProdService {
    private final ProductRepo productRepo;
    private final SubCategoryRepo subCategoryRepo;

    public NoImgFormDB getByIdExceptImg(String id) {
        return productRepo.getById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
    }

    public Product getById(String id) {
        return productRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
    }

    @Transactional
    public void deleteProduct(String id) {
        Product product = productRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));

        SubCategory subCategory = product.getSubCategory();
        subCategory.removeProduct(product);
        subCategoryRepo.save(subCategory);
    }

    @Transactional
    public ResponseEntity<ResponseTemp> createProduct(MultipartFile file, Product product) {
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

    public void editProduct(Product product) {
        Product productFromDB = productRepo.findById(product.getId())
                .orElseThrow(getNoSuchElementExceptionSupplier(product));

        productFromDB.setName(product.getName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setRating(product.getRating());

        SubCategory subCategory = subCategoryRepo.findById(productFromDB.getSubCategory().getId())
                .orElseThrow(getNoSuchElementExceptionSupplier(product));

        subCategory.addProduct(productFromDB);
        subCategoryRepo.save(subCategory);
    }

    private Supplier<NoSuchElementException> getNoSuchElementExceptionSupplier(Product product) {
        return () -> new NoSuchElementException(
                String.format("Не верно указан ид = %s, при редактировании товара %s",
                        product.getId(),
                        product.getName()));
    }

    private Supplier<NoSuchElementException> getNoSuchElementExceptionSupplier(String id) {
        return () -> new NoSuchElementException("Не верно указан ИД товара из категории " + id);
    }
}
