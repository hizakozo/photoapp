package com.example.photoapp.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ArticlePostForm {
    private String userToken;
    private MultipartFile uploadImage;
    private String explanation;
    private List<String> tagList;
}
