package ru.skillsad.sad.domain.catalog;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.views.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class Category extends BaseEntity {

    @JsonView(View.IdAndName.class)
    @Column(unique = true)
    @NotBlank
    private String name;

    @JsonView(View.IdAndName.class)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "category",fetch = FetchType.EAGER)
    private List<SubCategory> subCategoryList;

    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public Category(@NotBlank String name, List<SubCategory> subCategoryList) {
        this.name = name;
        this.subCategoryList = subCategoryList;
    }
}
