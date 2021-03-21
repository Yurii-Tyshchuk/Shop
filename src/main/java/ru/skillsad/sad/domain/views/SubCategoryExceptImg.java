package ru.skillsad.sad.domain.views;

import java.util.List;

public interface SubCategoryExceptImg {
    Long getId();
    String getName();
    List<NoImgFormDB> getProducts();
}
