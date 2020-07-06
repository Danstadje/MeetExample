package com.adatb2.meet.repository;

import com.adatb2.meet.model.Coupon;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends Repository<Coupon> {

    @Query("SELECT COUNT(c) FROM Coupon c")
    int getMaxCoupon();
}
