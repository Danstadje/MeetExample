package com.adatb2.meet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "MUser")
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private Date birthday;
    private String gender;

    @Column(name = "img_url")
    private String imgUrl;
    private String email;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "is_admin")
    private char isAdmin;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "auth_id")
    private Authentication authentication;

    public User() {
    }
}
