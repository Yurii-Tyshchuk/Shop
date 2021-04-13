package ru.skillsad.sad.controller.security;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.skillsad.sad.domain.general.MainText;
import ru.skillsad.sad.exception.ResponseTemp;
import ru.skillsad.sad.repository.MainTextRepo;

@RestController
@RequestMapping("/security")
public class GenController {

    private final MainTextRepo mainTextRepo;

    public GenController(MainTextRepo mainTextRepo) {
        this.mainTextRepo = mainTextRepo;
    }

    @PostMapping(value = "/editConOrMainText/{id}")
    public ResponseEntity<ResponseTemp> editContactText(@Valid @RequestBody MainText text,
                                                        @PathVariable String id) {
        MainText textFromDB = mainTextRepo.getById(Long.valueOf(id)) == null
                ? new MainText()
                : mainTextRepo.getById(Long.valueOf(id));
        textFromDB.setText(text.getText());
        mainTextRepo.save(textFromDB);
        return new ResponseEntity<>(new ResponseTemp("Отредактировано под ид = " + id), HttpStatus.OK);
    }
}
