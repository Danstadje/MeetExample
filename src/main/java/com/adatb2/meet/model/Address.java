package com.adatb2.meet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Address")
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id")
    private String addressId;
    private String country;
    private String zip;
    private String town;

    public Address() {
    }

    @Override
    public String toString() {
        return country + ", " + town + " (" + zip + ")";
    }
}
