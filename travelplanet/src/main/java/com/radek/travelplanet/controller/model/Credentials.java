package com.radek.travelplanet.controller.model;

public class Credentials {

    private String username;
    private String password;

    public Credentials() {
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }
}
