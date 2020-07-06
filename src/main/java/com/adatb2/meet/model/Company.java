package com.adatb2.meet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Company")
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name")
    private String companyName;
    private String bio;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "auth_id")
    private Authentication authentication;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Company() {
    }
}
