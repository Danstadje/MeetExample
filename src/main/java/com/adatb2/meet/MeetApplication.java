package com.adatb2.meet;

import com.adatb2.meet.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeetApplication {

    public static User loggedInUser;

    public static void main(String[] args) {
        SpringApplication.run(MeetApplication.class, args);
    }


}
