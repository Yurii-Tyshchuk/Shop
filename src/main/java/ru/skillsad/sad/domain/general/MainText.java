package ru.skillsad.sad.domain.general;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.skillsad.sad.domain.views.View;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
public class MainText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Id.class)
    private Long id;

    @Size(max = 3000)
    private String text;

    public MainText(String text) {
        this.text = text;

    }

    public MainText(String text, Long id) {
        this.text = text;
        this.id = id;
    }

    public MainText() {
    }
}
