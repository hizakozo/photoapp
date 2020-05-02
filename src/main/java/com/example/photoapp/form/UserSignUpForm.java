package com.example.photoapp.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserSignUpForm {
    private String userName;
    private String loginId;
    private String password;
    private MultipartFile imagePath;
}
