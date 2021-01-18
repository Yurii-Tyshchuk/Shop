package ru.skillsad.sad.domain.contact;

import lombok.Data;
import ru.skillsad.sad.domain.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class Contact extends BaseEntity {
    @NotBlank
    @Size(max = 3000)
    private String info;

    public Contact(@NotBlank @Size(max = 3000) String info) {
        this.info = info;
    }

    public Contact() {
    }
}
