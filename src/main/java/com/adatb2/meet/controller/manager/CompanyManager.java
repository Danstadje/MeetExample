package com.adatb2.meet.controller.manager;

import com.adatb2.meet.model.Company;
import com.adatb2.meet.model.Coupon;
import com.adatb2.meet.repository.CompanyRepository;
import com.adatb2.meet.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyManager {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyManager(CouponRepository couponRepository, CompanyRepository companyRepository) {
        this.couponRepository = couponRepository;
        this.companyRepository = companyRepository;
    }

    public Iterable<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Iterable<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
