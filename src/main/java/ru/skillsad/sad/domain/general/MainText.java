package ru.skillsad.sad.domain.general;

import lombok.Data;
import ru.skillsad.sad.domain.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Data
public class MainText extends BaseEntity {

    @Size(max = 3000)
    private String text;

    public MainText(String text) {
        this.text = text;

    }

    public MainText(String text, Long id) {
        this.text = text;
        super.setId(id);
    }

    public MainText() {
    }
}
