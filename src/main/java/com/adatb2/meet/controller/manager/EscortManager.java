package com.adatb2.meet.controller.manager;

import com.adatb2.meet.MeetApplication;
import com.adatb2.meet.model.Escort;
import com.adatb2.meet.model.Services;
import com.adatb2.meet.model.User;
import com.adatb2.meet.model.viewmodels.EscortRegistrationModel;
import com.adatb2.meet.repository.EscortRepository;
import com.adatb2.meet.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EscortManager {

    private final ServicesRepository servicesRepository;
    private final EscortRepository escortRepository;

    @Autowired
    public EscortManager(ServicesRepository servicesRepository, EscortRepository escortRepository) {
        this.servicesRepository = servicesRepository;
        this.escortRepository = escortRepository;
    }

    public Iterable<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public boolean register(EscortRegistrationModel escortRegistrationModel) {
        int newEscortId = Integer.parseInt(escortRepository.getMaxId()) + 1;
        User user = MeetApplication.loggedInUser;
        System.out.println(user);

        Escort escort = new Escort(String.valueOf(newEscortId)
                , user
                , escortRegistrationModel.getHourlyWage()
                , escortRegistrationModel.getBio()
                , Arrays.asList(escortRegistrationModel.getServices()));
        escortRepository.save(escort);
        return true;
    }

    public String getMaxId() {
        return escortRepository.getMaxId();
    }

    public boolean isEscort(String userId) {
        return escortRepository.checkIfEscort(userId) == 1;
    }

    public Escort getEscortByUserId(String userId) {
        return escortRepository.findByUserId(userId);
    }

    public Iterable<Escort> getAllEscort() {
        return escortRepository.findAll();
    }
}
