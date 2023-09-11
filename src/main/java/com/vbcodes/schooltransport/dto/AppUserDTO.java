package com.vbcodes.schooltransport.dto;

public class AppUserDTO {
    protected String username;
    protected String password;
    protected String roles;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "AppUserDTO [username=" + username + ", password=" + password + ", roles=" + roles + "]";
    }
}