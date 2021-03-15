package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.ProductRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

@Service
@AllArgsConstructor
public class ProdService {
    private final ProductRepo productRepo;
    private final SubCategoryRepo subCategoryRepo;

    @Transactional(readOnly = true)
    public Product getById(String id) {
        return productRepo.getById(Long.valueOf(id));
    }

    public void deleteProduct(String id){
        Product product = productRepo.getById(Long.valueOf(id));
        SubCategory subCategory = product.getSubCategory();
        subCategory.removeProduct(product);
        subCategoryRepo.save(subCategory);
    }
}
