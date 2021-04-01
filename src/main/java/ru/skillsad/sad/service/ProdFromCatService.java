package ru.skillsad.sad.service;

import org.springframework.stereotype.Service;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.ProductFromCategory;
import ru.skillsad.sad.repository.catalog.CategoryRepo;
import ru.skillsad.sad.repository.catalog.ProductFromCategoryRepo;

@Service
public class ProdFromCatService extends AbstractProdService<Category, ProductFromCategory, CategoryRepo, ProductFromCategoryRepo> {

    protected ProdFromCatService(CategoryRepo repositoryTop, ProductFromCategoryRepo productRepo) {
        super(repositoryTop, productRepo);
    }
}
