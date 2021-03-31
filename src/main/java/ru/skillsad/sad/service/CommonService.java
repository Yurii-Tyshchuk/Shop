package ru.skillsad.sad.service;

import ru.skillsad.sad.domain.BaseEntity;

public interface CommonService<E extends BaseEntity> {
    void updateName(E entity);

    void create(E entity);

    void deleteById(String id);
}
