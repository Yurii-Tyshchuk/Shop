package ru.skillsad.sad.service;

import org.springframework.stereotype.Service;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.repository.catalog.ProductRepo;
import ru.skillsad.sad.repository.catalog.SubCategoryRepo;

@Service
public class ProdService extends AbstractProdService<SubCategory, Product, SubCategoryRepo, ProductRepo> {

    protected ProdService(SubCategoryRepo repositoryTop, ProductRepo productRepo) {
        super(repositoryTop, productRepo);
    }
}
