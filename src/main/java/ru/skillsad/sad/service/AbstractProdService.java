package ru.skillsad.sad.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.BottomEntity;
import ru.skillsad.sad.domain.RemoveAndCreate;
import ru.skillsad.sad.domain.views.NoImgFormDB;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.CommonRepository;
import ru.skillsad.sad.repository.CustomQueryProduct;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * Абстракция необходимая для уменьшения копи-паста кода в продукте
 * <p>{@code public class ProdService extends AbstractProdService<SubCategory, Product, SubCategoryRepo, ProductRepo>}
 * @param <T> Сущность "над продуктом", см. пример в описании
 * @param <B> Сущность продукта
 * @param <R> Репозиторий сущности "над продуктом"
 * @param <E> Репозиторий сущности продукта
 */
public abstract class AbstractProdService<T extends BaseEntity & RemoveAndCreate<T, B>, B extends BaseEntity & BottomEntity<T>,
        R extends CommonRepository<T>, E extends CommonRepository<B> & CustomQueryProduct> {

    private final R repositoryTop;
    private final E productRepo;

    protected AbstractProdService(R repositoryTop, E productRepo) {
        this.repositoryTop = repositoryTop;
        this.productRepo = productRepo;
    }

    public NoImgFormDB getByIdExceptImg(String id) {
        return productRepo.getById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
    }

    public B getById(String id) {
        return productRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
    }

    @Transactional
    public void deleteProduct(String id) {
        B product = productRepo.findById(Long.valueOf(id))
                .orElseThrow(getNoSuchElementExceptionSupplier(id));
        T subCategory = product.getUpEntity();
        subCategory.removeProduct(product);
        repositoryTop.save(subCategory);
    }

    @Transactional
    public ResponseEntity<ResponseTemp> createProduct(MultipartFile file, B product) {
        try {
            product.setImg(file.getBytes());
            T entity = product.getUpEntity();
            entity.addProduct(product);
            repositoryTop.save(entity);
            return new ResponseEntity<>(new ResponseTemp("Картинка загружена"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(
                    new ResponseTemp("Не удалось загрузить => " + file.getOriginalFilename()),
                    HttpStatus.CONFLICT);
        }
    }

    private Supplier<NoSuchElementException> getNoSuchElementExceptionSupplier(String id) {
        return () -> new NoSuchElementException("Не верно указан ИД товара из категории " + id);
    }
}
