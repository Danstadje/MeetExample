package com.adatb2.meet.controller.view;

import com.adatb2.meet.MeetApplication;
import com.adatb2.meet.controller.manager.CompanyManager;
import com.adatb2.meet.controller.manager.EscortManager;
import com.adatb2.meet.model.User;
import com.adatb2.meet.model.viewmodels.CouponAddModel;
import com.adatb2.meet.model.viewmodels.SettingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final EscortManager escortManager;
    private final CompanyManager companyManager;

    @Autowired
    public MainController(EscortManager escortManager, CompanyManager companyManager) {
        this.escortManager = escortManager;
        this.companyManager = companyManager;
    }

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/login";
    }

    @RequestMapping("/home")
    public String mainPage() {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        if (MeetApplication.loggedInUser.getIsAdmin() == 'T') return "redirect:/admin";
        return "main";
    }

    @RequestMapping("admin")
    public String admin(Model model) {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        model.addAttribute("couponAddModel", new CouponAddModel());
        model.addAttribute("companies", companyManager.getAllCompanies());
        return "admin";
    }

    @RequestMapping("/messages")
    public String messages() {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        return "messages";
    }

    @RequestMapping("/coupon")
    public String coupon(Model model) {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        model.addAttribute("coupons", companyManager.getAllCoupons());
        return "coupon";
    }

    @RequestMapping("/escort")
    public String escort(Model model) {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        model.addAttribute("escorts", escortManager.getAllEscort());
        return "escort";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        User user = MeetApplication.loggedInUser;
        SettingsModel settingsModel = new SettingsModel(
                user.getAuthentication().getUsername(),
                user.getAuthentication().getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(),
                user.getAddress().getCountry(),
                user.getAddress().getZip(),
                user.getAddress().getTown(),
                user.getGender(),
                escortManager.isEscort(user.getUserId())
        );
        model.addAttribute("settingsModel", settingsModel);
        return "settings";
    }
}
