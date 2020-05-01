package com.example.photoapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private Integer userId;
    private String userName;
    private String imagePath;
}
