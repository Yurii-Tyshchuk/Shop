package ru.skillsad.sad.domain.catalog;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.BottomEntity;
import ru.skillsad.sad.domain.views.View;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class ProductFromCategory extends BaseEntity implements Serializable, BottomEntity<Category> {

    @JsonView(View.IdAndName.class)
    @NotBlank(message = "Имя продукта не может быть пустым")
    private String name;

    @JsonView(View.IdAndName.class)
    @NotBlank(message = "Описание продукта не может быть пустым")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category categoryy;

    @JsonView(View.IdAndName.class)
    @NotNull(message = "Рейтинг продукта не может быть пустым")
    private byte rating;

    @JsonView(View.IdAndNameAndImg.class)
    @NotBlank(message = "Изображение продукта не может быть пустым")
    private String imgName;

    @JsonView(View.IdAndNameAndImg.class)
    @NotNull
    @Lob
    private byte[] img;

    public ProductFromCategory() {
    }

    @Override
    public Category getUpEntity() {
        return categoryy;
    }

    @Override
    public void setUpEntity(Category entity) {
        this.categoryy = entity;
    }
}
