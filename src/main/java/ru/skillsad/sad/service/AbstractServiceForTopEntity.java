package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.repository.CommonAbstractRepository;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

@AllArgsConstructor
public abstract class AbstractServiceForTopEntity<E extends BaseEntity, R extends CommonAbstractRepository<E>> implements CommonService<E> {
    private final R repository;
    private final Supplier<E> supplier;

    @Override
    @Transactional
    public void updateName(E entity) {
        E entityFromDB = repository.findById(entity.getId())
                .orElseThrow(() -> new NoSuchElementException("Не верно указан ИД " + entity.getId() + entity.getName()));
        entityFromDB.setName(entity.getName());
        repository.save(entityFromDB);
    }

    @Override
    public void create(E entity) {
        E entity1 = supplier.get();
        entity1.setName(entity.getName());
        repository.save(entity1);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(Long.valueOf(id));
    }
}
