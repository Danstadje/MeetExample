package com.adatb2.meet.controller.view;

import com.adatb2.meet.controller.manager.EscortManager;
import com.adatb2.meet.controller.manager.LoginManager;
import com.adatb2.meet.model.Authentication;
import com.adatb2.meet.model.Services;
import com.adatb2.meet.model.viewmodels.EscortRegistrationModel;
import com.adatb2.meet.model.viewmodels.RegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginManager loginManager;
    private final EscortManager escortManager;

    @Autowired
    public LoginController(LoginManager loginManager, EscortManager escortManager) {
        this.loginManager = loginManager;
        this.escortManager = escortManager;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@ModelAttribute("auth")Authentication authentication) {
        if (loginManager.login(authentication)) return "redirect:/home";
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("registrationModel")RegistrationModel registrationModel) {
        boolean success = false;
        if (loginManager.register(registrationModel)) success = true;
        if (registrationModel.isEscort()) return "redirect:/escort_register";
        if (success) return "redirect:/home";
        return "register";
    }

    @GetMapping("/escort_register")
    public String escortRegister(Model model) {
        model.addAttribute("escortRegistrationModel", new EscortRegistrationModel());
        model.addAttribute("servicesList", escortManager.getAllServices());
        return "escort_reg";
    }

    @PostMapping("/escort_register")
    public String escortRegistration(@ModelAttribute("escortRegistrationModel") EscortRegistrationModel escortRegistrationModel) {
        if (escortManager.register(escortRegistrationModel)) return "redirect:/home";
        return "escort_reg";
    }
}
