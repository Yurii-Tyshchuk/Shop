package ru.skillsad.sad.domain;

import com.fasterxml.jackson.annotation.JsonView;
import ru.skillsad.sad.domain.views.View;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Id.class)
    private Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract String getName();

    public abstract void setName(String name);
}
