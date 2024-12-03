package com.example.pratham.Secure.User.Management.System.Project.dto;

public class UserDto {
    private  Long id;
    private String username;
    private String email;


    public UserDto( Long id, String username,String email) {
        this.email = email;
        this.id = id;
        this.username = username;

    }

    public UserDto(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
