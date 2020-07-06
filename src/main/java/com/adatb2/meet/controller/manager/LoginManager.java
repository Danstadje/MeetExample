package com.adatb2.meet.controller.manager;

import com.adatb2.meet.MeetApplication;
import com.adatb2.meet.model.Address;
import com.adatb2.meet.model.Authentication;
import com.adatb2.meet.model.User;
import com.adatb2.meet.model.viewmodels.RegistrationModel;
import com.adatb2.meet.repository.AddressRepository;
import com.adatb2.meet.repository.AuthenticationRepository;
import com.adatb2.meet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class LoginManager {

    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public LoginManager(AuthenticationRepository authenticationRepository, UserRepository userRepository, AddressRepository addressRepository) {
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public boolean login(Authentication authentication) {
        String auth_id = authenticationRepository.checkLogin(authentication.getUsername(), authentication.getPassword());
        User user = userRepository.checkLogin(auth_id);
        MeetApplication.loggedInUser = user;
        return user != null;
    }

    public boolean register(RegistrationModel registrationModel) {
        User lastEntry = userRepository.getLastEntry();
        int newUserId = Integer.parseInt(lastEntry.getUserId()) + 1;
        int newAuthId = Integer.parseInt(lastEntry.getAuthentication().getAuthId()) + 1;
        int newAddressId = Integer.parseInt(lastEntry.getAddress().getAddressId()) + 1;

        Address address = new Address(String.valueOf(newAddressId), registrationModel.getCountry(), registrationModel.getZip(), registrationModel.getTown());

        Authentication authentication = new Authentication(String.valueOf(newAuthId), registrationModel.getUsername(), registrationModel.getPassword());

        User user = new User(String.valueOf(newUserId), registrationModel.getFirstName(), registrationModel.getLastName()
                , registrationModel.getBirthday(), registrationModel.getGender(), null, registrationModel.getEmail()
                , new Date(new java.util.Date().getTime()), 'F', address, authentication);

        MeetApplication.loggedInUser = user;
        addressRepository.save(address);
        authenticationRepository.save(authentication);
        return userRepository.save(user) != null;
    }
}
