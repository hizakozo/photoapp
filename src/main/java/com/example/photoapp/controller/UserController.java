package com.example.photoapp.controller;

import com.example.photoapp.Service.UserService;
import com.example.photoapp.exception.PhotoAppException;
import com.example.photoapp.exception.UniqueException;
import com.example.photoapp.form.UserSignInForm;
import com.example.photoapp.form.UserSignUpForm;
import com.example.photoapp.response.UserSignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/signUp")
    public void signUp(@RequestBody UserSignUpForm form) throws UniqueException {
        userService.signUp(form.getUserName(), form.getLoginId(), form.getPassword(), form.getImagePath());
    }

    @ResponseBody
    @PostMapping("/signIn")
    public UserSignInResponse signIn(@RequestBody UserSignInForm form) throws PhotoAppException {
        String loginId = form.getLoginId();
        String password = form.getPassword();
        return userService.signIn(loginId, password);
    }
}