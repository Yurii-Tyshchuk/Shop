package ru.skillsad.sad.domain.catalog;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.views.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SubCategory extends BaseEntity {

    @JsonView(View.IdAndName.class)
    @Column(unique = true)
    @NotBlank
    private String name;

    @ManyToOne(optional = false)
//    @JoinColumn(name = "category_id")
    private Category category;

    @JsonView(View.IdAndName.class)
    @OneToMany(orphanRemoval = true,mappedBy = "subCategory",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
        product.setSubCategory(this);
    }
    public void removeProduct(Product product){
        products.remove(product);
        product.setSubCategory(null);
    }

    public SubCategory() {
    }

    public SubCategory(@NotBlank String name) {
        this.name = name;
    }

    public SubCategory(@NotBlank String name, Category category) {
        this.name = name;
        this.category = category;
    }
}
