package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.repository.CategoryRepo;
import ru.skillsad.sad.repository.ProductFromCategoryRepo;

@Service
@AllArgsConstructor
public class ProdFromCatService {

    private final CategoryRepo categoryRepo;
    private final ProductFromCategoryRepo productFromCategoryRepo;

    @Transactional(readOnly = true)
    public ProductFromCategory getById(String id){
        return productFromCategoryRepo.getById(Long.valueOf(id));
    }

    public void deleteProduct(String id){
        ProductFromCategory product = productFromCategoryRepo.getById(Long.valueOf(id));
        Category category = product.getCategoryy();
        category.removeProduct(product);
        categoryRepo.save(category);
    }

}
