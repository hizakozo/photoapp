package com.example.photoapp.form;

import lombok.Data;

@Data
public class UserSignUpForm {
    private String userName;
    private String loginId;
    private String password;
    private String imagePath;
}
