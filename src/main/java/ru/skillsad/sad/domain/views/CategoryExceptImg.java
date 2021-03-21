package ru.skillsad.sad.domain.views;

import java.util.List;

public interface CategoryExceptImg {
    Long getId();
    String getName();
    List<NoImgFormDB> getProducts();
    List<SubCategoryExceptImg> getSubCategoryList();
}
