package com.adatb2.meet.model.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class CouponAddModel {

    private String companyId;
    private String couponDesc;
    private Date startDate;
    private Date endDate;
    private int amount;

    public CouponAddModel() {
    }
}
