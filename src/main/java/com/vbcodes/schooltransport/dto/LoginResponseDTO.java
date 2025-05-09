package com.vbcodes.schooltransport.dto;

import java.util.List;

public class LoginResponseDTO {
    private String username;
    private String token;
    private String message;
    private List<String> role;

    public LoginResponseDTO(String username, String token, String message, List<String> role) {
        this.username = username;
        this.token = token;
        this.message = message;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<String> getRole() {
        return role;
    }
    public void setRole(List<String> role) {
        this.role = role;
    }
    
}
