package ru.skillsad.sad.controller.security;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillsad.sad.domain.general.MainText;
import ru.skillsad.sad.repository.MainTextRepo;

import javax.validation.Valid;

@RestController
@RequestMapping("/security")
public class GenController {

    private final MainTextRepo mainTextRepo;

    public GenController(MainTextRepo mainTextRepo) {
        this.mainTextRepo = mainTextRepo;
    }

    @PostMapping(value = "/editGeneral")
    public MainText editMain(@Valid @RequestBody MainText text) {
        MainText textFromDB = mainTextRepo.getById(1L);
        BeanUtils.copyProperties(text, textFromDB, "id");
        return mainTextRepo.save(textFromDB);
    }
}
