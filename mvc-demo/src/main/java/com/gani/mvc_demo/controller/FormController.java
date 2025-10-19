package com.gani.mvc_demo.controller;

import com.gani.mvc_demo.entity.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @GetMapping("/show-form")
    public String showForm(Model model) {

        UserData userData = new UserData("sprk","technologies");

//        model.addAttribute("userData",new UserData());
        model.addAttribute("userData",userData);

        return "user_input";
    }

    @PostMapping("/process-form")
    public String processForm(@ModelAttribute UserData userData, Model model){

        model.addAttribute("userData",userData);

        return "user_info";
    }
}
