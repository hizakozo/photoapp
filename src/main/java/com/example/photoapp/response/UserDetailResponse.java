package com.example.photoapp.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailResponse {
    private Integer userId;
    private String userName;
    private String imagePath;
}
