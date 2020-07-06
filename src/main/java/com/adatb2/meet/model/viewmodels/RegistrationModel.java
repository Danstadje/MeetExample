package com.adatb2.meet.model.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class RegistrationModel {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private String country;
    private String zip;
    private String town;
    private String gender;
    private boolean escort;

    public RegistrationModel() {
    }
}
