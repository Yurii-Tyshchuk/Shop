package ru.skillsad.sad.repository.catalog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.repository.CommonAbstractRepository;

import java.util.Optional;
@Repository
public interface ProductRepo extends CommonAbstractRepository<Product> {
    @Query(value = "select\n" +
            "        product.id,\n" +
            "        product.name,\n" +
            "        product.description,\n" +
            "        product.rating \n" +
            "    from\n" +
            "        Product as product \n" +
            "    where\n" +
            "        product.id=:id", nativeQuery = true)
    Optional<NoImgFormDB> getById(@Param("id") Long id);
}
