package com.vbcodes.schooltransport.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AppUserDTO {

    protected String username;
    @ToString.Exclude
    protected String password;
    protected String roles;

}