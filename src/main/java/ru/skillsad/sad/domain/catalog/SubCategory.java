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
public class SubCategory extends BaseEntity {

    @JsonView(View.IdAndName.class)
    @Column(unique = true)
    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonView(View.IdAndName.class)
    @OneToMany(orphanRemoval = true,mappedBy = "subCategory",fetch = FetchType.EAGER)
    private List<Product> products;

    public SubCategory() {
    }

    public SubCategory(@NotBlank String name) {
        this.name = name;
    }

    public SubCategory(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public SubCategory(@NotBlank String name, Category category) {
        this.name = name;
        this.category = category;
    }
}
