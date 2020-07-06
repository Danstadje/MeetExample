package com.adatb2.meet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Escort")
public class Escort {

    @Id
    @Column(name = "escort_id")
    private String escortId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "hourly_wage")
    private int hourlyWage;

    private String bio;

    @ManyToMany
    @JoinTable(name = "ESCORTSERVICES",
        joinColumns = @JoinColumn(name = "escort_id", referencedColumnName = "escort_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "service_id"))
    private List<Services> servicesList;

    public Escort() {
    }

    public Escort(String escortId, User user, int hourlyWage, String bio, List<Services> servicesList) {
        this.escortId = escortId;
        this.user = user;
        this.hourlyWage = hourlyWage;
        this.bio = bio;
        this.servicesList = servicesList;
        this.servicesList.forEach(x -> x.getEscorts().add(this));
    }
}
