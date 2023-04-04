package ru.skillsad.sad.domain.catalog;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.skillsad.sad.domain.BaseEntity;
import ru.skillsad.sad.domain.views.View;

@Entity
@Setter
@Getter
public class Product extends BaseEntity {
    @JsonView(View.IdAndName.class)
    @NotBlank(message = "Имя продукта не может быть пустым")
    private String name;

    @JsonView(View.IdAndName.class)
    @NotBlank(message = "Описание продукта не может быть пустым")
    private String description;

    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;

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

    public Product() {
    }

    public Product(@NotBlank String name, @NotBlank String description, SubCategory subCategory, @NotNull byte rating,
            @NotBlank String imgName, @NotNull byte[] img) {
        this.name = name;
        this.description = description;
        this.subCategory = subCategory;
        this.rating = rating;
        this.imgName = imgName;
        this.img = img;
    }

    public Product(@NotBlank(message = "Имя продукта не может быть пустым") String name,
            @NotBlank(message = "Описание продукта не может быть пустым") String description,
            @NotNull(message = "Рейтинг продукта не может быть пустым") byte rating,
            @NotBlank(message = "Изображение продукта не может быть пустым") String imgName, @NotNull byte[] img) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imgName = imgName;
        this.img = img;
    }
}
