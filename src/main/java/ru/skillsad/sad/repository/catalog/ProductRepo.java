package ru.skillsad.sad.repository.catalog;

import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.repository.CommonRepository;
import ru.skillsad.sad.repository.CustomQueryProduct;

@Repository
public interface ProductRepo extends CommonRepository<Product>, CustomQueryProduct {
//    @Query(value = "select\n" +
//            "        product.id,\n" +
//            "        product.name,\n" +
//            "        product.description,\n" +
//            "        product.rating \n" +
//            "    from\n" +
//            "        Product as product \n" +
//            "    where\n" +
//            "        product.id=:id", nativeQuery = true)
//    Optional<NoImgFormDB> getById(@Param("id") Long id);
}
