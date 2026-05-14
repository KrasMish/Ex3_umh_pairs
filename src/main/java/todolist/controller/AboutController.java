package todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String aboutPage(Model model) {

        model.addAttribute("team", "Krasin Mykhailo");
        model.addAttribute("version", "v1.1.0");
        model.addAttribute("date", "14 May 2026");

        return "about";
    }
}