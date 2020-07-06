package com.adatb2.meet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Coupons")
@AllArgsConstructor
public class Coupon {

    @Id
    @Column(name = "coupon_id")
    private String couponId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private String discount;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Coupon() {
    }
}
