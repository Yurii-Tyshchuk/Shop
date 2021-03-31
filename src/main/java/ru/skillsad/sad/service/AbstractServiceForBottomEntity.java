package ru.skillsad.sad.service;

import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.repository.CommonAbstractRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public abstract class AbstractServiceForBottomEntity<T extends BaseEntity, B extends BaseEntity,
        R extends CommonAbstractRepository<T>, E extends CommonAbstractRepository<B>> extends AbstractServiceForTopEntity<T, R> {

    private final E repositoryBottom;

    public AbstractServiceForBottomEntity(R repository, Supplier<T> supplier, E repositoryBottom) {
        super(repository, supplier);
        this.repositoryBottom = repositoryBottom;
    }

    @Override
    public void deleteById(String id) {

        B bottomEntity = repositoryBottom.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("Не верно указан ИД товара из категории" + id));
//        T topEntity = (T) bottomEntity.getUpEntity();
    }
}
