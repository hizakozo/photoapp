package com.example.photoapp.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticlePostForm extends UserTokenForm{
    private MultipartFile uploadImage;
    private String explanation;
    private List<String> tagList;
}
