package com.example.photoapp.Service;

import com.example.photoapp.dao.AuthDao;
import com.example.photoapp.dao.UserDao;
import com.example.photoapp.entity.Auth;
import com.example.photoapp.entity.User;
import com.example.photoapp.exception.*;
import com.example.photoapp.model.UserModel;
import com.example.photoapp.response.UserDetailResponse;
import com.example.photoapp.response.UserSignInResponse;
import com.example.photoapp.utils.ImgUtil;
import com.example.photoapp.utils.PasswordUtil;
import com.example.photoapp.utils.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Service
public class UserService {
    private final UserDao userDao;
    private final AuthDao authDao;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper mapper;
    @Autowired
    public UserService(UserDao userDao, AuthDao authDao, StringRedisTemplate redisTemplate, ObjectMapper mapper) {
        this.userDao = userDao;
        this.authDao = authDao;
        this.redisTemplate = redisTemplate;
        this.mapper = mapper;
    }

    @Transactional
    public String signUp (String userName, String loginId, String password, String imageName) throws UniqueException {
        String uniqueName = ImgUtil.getUniqueFileName("user/", imageName);
        String safetyPassword = PasswordUtil.getSafetyPassword(password, loginId);
        if (authDao.findByLoginId(loginId).isPresent()) {
            throw new UniqueException();
        }

        var user = new User();
        user.setUserName(userName);
        user.setImagePath(uniqueName);
        userDao.insert(user);

        var auth = new Auth();
        auth.setUserId(user.getUserId());
        auth.setLoginId(loginId);
        auth.setPassword(safetyPassword);
        authDao.insert(auth);
        return uniqueName;
    }

    public UserSignInResponse signIn(String loginId, String password) throws PhotoAppException {
        String safetyPassword = PasswordUtil.getSafetyPassword(password, loginId);
        Auth auth = authDao.findByLoginId(loginId).orElseThrow(NotFoundDataException::new);
        if (!auth.getPassword().equals(safetyPassword)){
            throw new DifferencePasswordException();
        }
        User user = userDao.findById(auth.getUserId()).orElseThrow(NotFoundDataException::new);
        UserModel userModel = UserModel.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .imagePath(user.getImagePath())
                .build();
        String userToken = TokenUtil.getCsrfToken();
        writeUserInfo2Redis(userModel, userToken);
        return UserSignInResponse.builder().userToken(userToken).build();
    }
    public void writeUserInfo2Redis(UserModel userModel, String userToken) {
        String json = null;
        try {
            json = mapper.writeValueAsString(userModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(userToken, Objects.requireNonNull(json));
    }
    public UserModel readUserInfoFromRedis(String userToken) throws PhotoAppException{
        String json = null;
        try {
            json = redisTemplate.opsForValue().get(userToken);
        }catch (IllegalArgumentException e) {
            throw new TokenException();
        }
        UserModel userModel = null;
        try {
            userModel = mapper.readValue(Objects.requireNonNull(json), UserModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userModel;
    }
}
