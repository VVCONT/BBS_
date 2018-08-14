package com.thorn.springboost.model;

import lombok.Getter;
import lombok.Setter;

public class user {
    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Getter @Setter private String username;

    @Getter @Setter private String password;
}
