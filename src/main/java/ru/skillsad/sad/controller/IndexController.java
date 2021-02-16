package ru.skillsad.sad.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillsad.sad.domain.user.User;
import ru.skillsad.sad.service.CommandLineAppStartupRunner;

@Controller
@RequestMapping("/")
public class IndexController {

    private final CommandLineAppStartupRunner runner;

    public IndexController(CommandLineAppStartupRunner runner) throws Exception {
        this.runner = runner;
    }

    @GetMapping()
    public String main(Model model, @AuthenticationPrincipal User user) throws Exception {
        if (user != null) {
            model.addAttribute("profile", "active");
        } else {
            model.addAttribute("profile", "null");
        }
        return "index";
    }
}
