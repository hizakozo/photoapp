package com.example.photoapp.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticlePostResponse {
    private String imagePath;
}
