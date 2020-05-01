package com.example.photoapp.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserSignInResponse {
    private String userToken;
}
