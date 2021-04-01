package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.skillsad.sad.domain.BaseEntity;

import java.util.Optional;

@NoRepositoryBean
public interface CommonRepository<E extends BaseEntity> extends JpaRepository<E,Long> {
    Optional<E> findByName(String name);
}
