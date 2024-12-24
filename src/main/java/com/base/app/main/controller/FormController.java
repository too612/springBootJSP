package com.base.app.main.controller;


import com.base.app.main.data.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @GetMapping("/form.do")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "main/form";
    }

    @PostMapping("/submit.do")
    public String submitForm(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "main/result";
    }
}
