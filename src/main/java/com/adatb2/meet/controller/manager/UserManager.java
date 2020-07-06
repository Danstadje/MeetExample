package com.adatb2.meet.controller.manager;

import com.adatb2.meet.MeetApplication;
import com.adatb2.meet.model.Address;
import com.adatb2.meet.model.Authentication;
import com.adatb2.meet.model.User;
import com.adatb2.meet.model.viewmodels.SettingsModel;
import com.adatb2.meet.repository.AddressRepository;
import com.adatb2.meet.repository.AuthenticationRepository;
import com.adatb2.meet.repository.EscortRepository;
import com.adatb2.meet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    private final AddressRepository addressRepository;
    private final EscortRepository escortRepository;

    @Autowired
    public UserManager(UserRepository userRepository, AuthenticationRepository authenticationRepository, AddressRepository addressRepository, EscortRepository escortRepository) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
        this.addressRepository = addressRepository;
        this.escortRepository = escortRepository;
    }

    /**
     *
     * @return true if should navigate to escort_reg
     */
    public boolean updateUser(SettingsModel settingsModel) {
        System.out.println(settingsModel);
        Authentication authentication = authenticationRepository.findByUsername(settingsModel.getUsername());
        authentication.setUsername(settingsModel.getUsername());
        if (!("").equals(settingsModel.getPassword())) authentication.setPassword(settingsModel.getPassword());

        User user = userRepository.checkLogin(authentication.getAuthId());
        user.setFirstName(settingsModel.getFirstName());
        user.setLastName(settingsModel.getLastName());
        user.setBirthday(settingsModel.getBirthday());
        user.setGender(settingsModel.getGender());
        user.setEmail(settingsModel.getEmail());

        Address address = user.getAddress();
        address.setCountry(settingsModel.getCountry());
        address.setTown(settingsModel.getTown());
        address.setZip(settingsModel.getZip());

        user.setAddress(address);
        user.setAuthentication(authentication);

        MeetApplication.loggedInUser = user;

        addressRepository.updateAddress(address.getAddressId(), address.getCountry(), address.getTown(), address.getZip());
        authenticationRepository.updateAuth(authentication.getAuthId(), authentication.getUsername(), authentication.getPassword());
        userRepository.updateUser(user.getUserId(), user.getFirstName(), user.getLastName(), user.getBirthday(), user.getGender()
                , user.getEmail());

        if (escortRepository.checkIfEscort(user.getUserId()) == 0 && settingsModel.isEscort()) {
            return true;
        } else if (escortRepository.checkIfEscort(user.getUserId()) == 1 && !settingsModel.isEscort()) {
            escortRepository.delete(escortRepository.findByUserId(user.getUserId()));
        }
        return false;
    }
}
