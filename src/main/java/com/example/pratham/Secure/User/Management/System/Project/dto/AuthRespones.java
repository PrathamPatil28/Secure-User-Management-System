package com.example.pratham.Secure.User.Management.System.Project.dto;

public class AuthRespones {

    private String token;

    public AuthRespones(String token) {

        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
