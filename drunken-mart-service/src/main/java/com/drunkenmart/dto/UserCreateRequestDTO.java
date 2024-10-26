package com.drunkenmart.dto;

import lombok.Data;

@Data
public class UserCreateRequestDTO {

    private String username;

    private String password;

    private String gender;

    private String role;

    private String mobileNo;

    private String emailId;
}
