package com.adatb2.meet.model.viewmodels;

import com.adatb2.meet.model.Services;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EscortRegistrationModel {

    private int hourlyWage;
    private String bio;
    private Services[] services;

    public EscortRegistrationModel() {
    }
}
