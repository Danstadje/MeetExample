package com.adatb2.meet.controller.view;

import com.adatb2.meet.MeetApplication;
import com.adatb2.meet.controller.manager.UserManager;
import com.adatb2.meet.model.viewmodels.SettingsModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingsController {

    private final UserManager userManager;

    public SettingsController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/settings")
    public String saveChanges(@ModelAttribute("settingsModel")SettingsModel settingsModel) {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        if (userManager.updateUser(settingsModel)) return "redirect:/escort_register";
        return "redirect:/home";
    }
}
