package com.example.photoapp.controller;

import com.example.photoapp.Service.AmazonS3Service;
import com.example.photoapp.Service.UserService;
import com.example.photoapp.exception.PhotoAppException;
import com.example.photoapp.exception.UniqueException;
import com.example.photoapp.form.UserSignInForm;
import com.example.photoapp.form.UserSignUpForm;
import com.example.photoapp.form.UserTokenForm;
import com.example.photoapp.model.UserModel;
import com.example.photoapp.response.UserDetailResponse;
import com.example.photoapp.response.UserSignInResponse;
import com.example.photoapp.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AmazonS3Service amazonS3Service;

    @Autowired
    public UserController(UserService userService, AmazonS3Service amazonS3Service) {
        this.userService = userService;
        this.amazonS3Service = amazonS3Service;
    }

    @ResponseBody
    @PostMapping("/signUp")
    public void signUp(@ModelAttribute UserSignUpForm form) throws UniqueException, IOException {
        MultipartFile inputImage = form.getImagePath();
        String uniqueName = userService.signUp(form.getUserName(), form.getLoginId(), form.getPassword(), inputImage.getOriginalFilename());
        amazonS3Service.uploadToAmazonS3(inputImage, uniqueName);
    }

    @ResponseBody
    @PostMapping("/signIn")
    public UserSignInResponse signIn(@RequestBody UserSignInForm form) throws PhotoAppException {
        String loginId = form.getLoginId();
        String password = form.getPassword();
        return userService.signIn(loginId, password);
    }

    @ResponseBody
    @GetMapping("/detail")
    public UserDetailResponse detail(@RequestBody UserTokenForm form) throws PhotoAppException {
        UserModel userModel = userService.readUserInfoFromRedis(form.getUserToken());
        return UserDetailResponse.builder()
                .userId(userModel.getUserId())
                .userName(userModel.getUserName())
                .imagePath(userModel.getImagePath())
                .build();
    }
}