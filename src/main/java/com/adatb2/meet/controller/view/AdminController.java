package com.adatb2.meet.controller.view;

import com.adatb2.meet.MeetApplication;
import com.adatb2.meet.model.Coupon;
import com.adatb2.meet.model.viewmodels.CouponAddModel;
import com.adatb2.meet.repository.CompanyRepository;
import com.adatb2.meet.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public AdminController(CouponRepository couponRepository, CompanyRepository companyRepository) {
        this.couponRepository = couponRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping("/addCoupon")
    public String addCoupon(@ModelAttribute("couponAddModel")CouponAddModel couponAddModel) {
        if (MeetApplication.loggedInUser == null) return "redirect:/login";
        Coupon coupon = new Coupon(String.valueOf(couponRepository.getMaxCoupon()+1), couponAddModel.getStartDate(), couponAddModel.getEndDate()
                , couponAddModel.getCouponDesc(), couponAddModel.getAmount(), companyRepository.findById(couponAddModel.getCompanyId()).get());

        couponRepository.save(coupon);
        return "redirect:/admin";
    }
}
