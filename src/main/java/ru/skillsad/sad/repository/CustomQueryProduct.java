package ru.skillsad.sad.repository;

import ru.skillsad.sad.domain.views.NoImgFormDB;

import java.util.Optional;

public interface CustomQueryProduct {
    Optional<NoImgFormDB> getById(Long id);
}
