package ru.skillsad.sad.domain.catalog;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.views.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

//@NamedEntityGraph(name = "test",
//        attributeNodes = @NamedAttributeNode("name"),
//        subgraphs = {
//                @NamedSubgraph(
//                        name = "SubTest",
//                        attributeNodes = {
//                                @NamedAttributeNode("name")
//                        }
//                )
//        })

@Entity
@Data
public class Category extends BaseEntity {

    @JsonView(View.IdAndName.class)
    @Column(unique = true)
    @NotBlank
    private String name;

    @JsonView(View.IdAndName.class)
    @OneToMany(orphanRemoval = true, mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubCategory> subCategoryList = new ArrayList<>();

    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public Category(@NotBlank String name, List<SubCategory> subCategoryList) {
        this.name = name;
        this.subCategoryList = subCategoryList;
    }

    public void addSubCategory(SubCategory subCategory) {
        subCategoryList.add(subCategory);
        subCategory.setCategory(this);
    }

    public void removeSubCategory(SubCategory subCategory) {
        subCategoryList.remove(subCategory);
        subCategory.setCategory(null);
    }
}
