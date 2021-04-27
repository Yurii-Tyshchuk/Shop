package ru.skillsad.sad.domain.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.views.View;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "subCategoryList.products",
                attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode(value = "products", subgraph = "SubCategory.products"),
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "SubCategory.products",
                                attributeNodes = {
                                        @NamedAttributeNode("id"),
                                        @NamedAttributeNode("name"),
                                        @NamedAttributeNode("description"),
                                        @NamedAttributeNode("rating")
                                })
                }
        )
})
@Entity
@Setter
@Getter
public class SubCategory extends BaseEntity {

    @JsonView(View.IdAndName.class)
    @NotBlank(message = "Имя подкатегории не должно быть пустым")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonView(View.IdAndName.class)
    @OneToMany(orphanRemoval = true, mappedBy = "subCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setSubCategory(this);
    }

    public void removeProduct(Product product) {
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
