package ru.skillsad.sad.domain.catalog;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.views.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@NamedEntityGraphs({
        @NamedEntityGraph(name = "testGraphs",
                attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode(value = "products", subgraph = "category.products"),
                        @NamedAttributeNode(value = "subCategoryList", subgraph = "category.subCategoryList")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "category.products",
                                attributeNodes = {
                                        @NamedAttributeNode("id"),
                                        @NamedAttributeNode("name"),
                                        @NamedAttributeNode("description"),
                                        @NamedAttributeNode("rating")
                                }
                        ),
                        @NamedSubgraph(
                                name = "category.subCategoryList",
                                attributeNodes = {
                                        @NamedAttributeNode("id"),
                                        @NamedAttributeNode("name"),
                                        @NamedAttributeNode(value = "products", subgraph = "subCategoryList.products"),
                                }
                        )
                }
        )
})
@Entity
@Data
public class Category extends BaseEntity {

    @JsonView(View.IdAndName.class)
    @Column(unique = true)
    @NotBlank
    private String name;

    @JsonView(View.IdAndName.class)
    @OneToMany(orphanRemoval = true, mappedBy = "categoryy", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<ProductFromCategory> products = new ArrayList<>();

    @JsonView(View.IdAndName.class)
    @OneToMany(orphanRemoval = true, mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<SubCategory> subCategoryList = new ArrayList<>();

    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public void addSubCategory(SubCategory subCategory) {
        subCategoryList.add(subCategory);
        subCategory.setCategory(this);
    }

    public void removeSubCategory(SubCategory subCategory) {
        subCategoryList.remove(subCategory);
        subCategory.setCategory(null);
    }

    public void addProduct(ProductFromCategory product) {
        products.add(product);
        product.setCategoryy(this);
    }

    public void removeProduct(ProductFromCategory product) {
        products.remove(product);
        product.setCategoryy(null);
    }
}
