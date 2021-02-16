package ru.skillsad.sad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.repository.ProductRepo;

import java.io.IOException;
import java.util.function.Function;

@Service
public class FIleStorageService {
    private final ProductRepo productRepo;

    public FIleStorageService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product saveImd(MultipartFile file, String id) throws IOException {
        Product product = productRepo.getById(Long.valueOf(id));
        product.setImg(file.getBytes());
        return productRepo.save(product);
    }



    @Transactional
    public Product getImg(String id) {
        return productRepo.getById(Long.valueOf(id));
    }
}
