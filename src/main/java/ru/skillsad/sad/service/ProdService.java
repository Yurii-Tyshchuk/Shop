package ru.skillsad.sad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.ProductRepo;
import ru.skillsad.sad.repository.SubCategoryRepo;

@Service
public class ProdService {

    private final ProductRepo productRepo;
    private final SubCategoryRepo subCategoryRepo;

    public ProdService(ProductRepo productRepo, SubCategoryRepo subCategoryRepo) {
        this.productRepo = productRepo;
        this.subCategoryRepo = subCategoryRepo;
    }

    @Transactional(readOnly = true)
    public Product getById(String id) {
        return productRepo.getById(Long.valueOf(id));
    }

    public void deleteProduct(String id){
        Product product = productRepo.getById(Long.valueOf(id));
        SubCategory subCategory = product.getSubCategory();
        subCategory.removeProduct(product);
        subCategoryRepo.save(subCategory);
//        productRepo.deleteById(Long.valueOf(id));
    }
}
