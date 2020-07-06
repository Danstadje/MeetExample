package com.adatb2.meet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Auth")
@AllArgsConstructor
public class Authentication {

    @Id
    @Column(name = "auth_id")
    private String authId;
    private String username;
    private String password;

    public Authentication() {
    }

    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
