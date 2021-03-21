package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.repository.ProductRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

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

    public Product getById(String id){
        return productRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
    }

    public void deleteProduct(String id) {
        Product product = productRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));

        SubCategory subCategory = product.getSubCategory();
        subCategory.removeProduct(product);
        subCategoryRepo.save(subCategory);
    }

    private Supplier<NoSuchElementException> getNoSuchElementExceptionSupplier(String id) {
        return () -> new NoSuchElementException("Не верно указан ИД товара из категории " + id);
    }
}
