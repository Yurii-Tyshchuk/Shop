package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.views.NoImgFormDB;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "select\n" +
            "        product.id,\n" +
            "        product.name,\n" +
            "        product.description,\n" +
            "        product.rating \n" +
            "    from\n" +
            "        Product as product \n" +
            "    where\n" +
            "        product.id=:id",nativeQuery = true)
    Optional<NoImgFormDB> getById(@Param("id") Long id);
}
