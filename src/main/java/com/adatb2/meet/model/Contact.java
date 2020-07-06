package com.adatb2.meet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Contact")
@AllArgsConstructor
public class Contact {

    @Id
    @Column(name = "contact_id")
    private String contactId;

    private String phone;
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Contact() {
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n" + phone + "\n" + email;
    }
}
