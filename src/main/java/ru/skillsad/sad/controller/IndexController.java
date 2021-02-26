package ru.skillsad.sad.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillsad.sad.domain.user.User;
import ru.skillsad.sad.service.CommandLineAppStartupRunner;

@Controller
@RequestMapping("/")
public class IndexController {

    private final CommandLineAppStartupRunner runner;
    @Value("${spring.profiles.active:prod}")
    private String profile;

    public IndexController(CommandLineAppStartupRunner runner) throws Exception {
        this.runner = runner;
        this.runner.runUser();
    }

    @GetMapping()
    @Transactional
    public String main(Model model, @AuthenticationPrincipal User user) throws Exception {
        if (user != null) {
            model.addAttribute("profile", "active");
        } else {
            model.addAttribute("profile", "null");
        }
        model.addAttribute("isDevMode", "dev".equals(profile));
        if ("dev".equals(profile)) runner.run();
        return "index";
    }

    @GetMapping("/Catalog")
    public String Catalog(Model model, @AuthenticationPrincipal User user) throws Exception {
        if (user != null) {
            model.addAttribute("profile", "active");
        } else {
            model.addAttribute("profile", "null");
        }
        return "redirect:/";
    }

    @GetMapping("/About")
    public String About(Model model, @AuthenticationPrincipal User user) throws Exception {
        if (user != null) {
            model.addAttribute("profile", "active");
        } else {
            model.addAttribute("profile", "null");
        }
        return "redirect:/";
    }
}
