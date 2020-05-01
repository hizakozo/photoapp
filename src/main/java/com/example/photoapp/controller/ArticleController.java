package com.example.photoapp.controller;

import com.example.photoapp.Service.AmazonS3Service;
import com.example.photoapp.Service.ArticleService;
import com.example.photoapp.Service.UserService;
import com.example.photoapp.exception.PhotoAppException;
import com.example.photoapp.form.ArticlePostForm;
import com.example.photoapp.model.UserModel;
import com.example.photoapp.response.ArticlePostResponse;
import com.example.photoapp.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Value("${s3.url-template}")
    private String urlTemplate;
    private final ArticleService articleService;
    private final UserService userService;
    private final AmazonS3Service amazonS3Service;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService, AmazonS3Service amazonS3Service) {
        this.articleService = articleService;
        this.userService = userService;
        this.amazonS3Service = amazonS3Service;
    }

    @ResponseBody
    @PostMapping("/post")
    public ArticlePostResponse post(@ModelAttribute ArticlePostForm form) throws IOException, PhotoAppException {
        UserModel userModel = userService.readUserInfoFromRedis(form.getUserToken());
        String uniqueFileName = articleService.post(userModel.getUserId(), form);
        amazonS3Service.uploadToAmazonS3(form.getUploadImage(), uniqueFileName);
        return ArticlePostResponse.builder().imagePath(urlTemplate + uniqueFileName).build();
    }
}
