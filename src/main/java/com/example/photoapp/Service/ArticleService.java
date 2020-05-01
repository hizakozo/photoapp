package com.example.photoapp.Service;

import com.example.photoapp.dao.*;
import com.example.photoapp.entity.Image;
import com.example.photoapp.entity.ImageTag;
import com.example.photoapp.entity.Tag;
import com.example.photoapp.form.ArticlePostForm;
import com.example.photoapp.response.ArticlePostResponse;
import com.example.photoapp.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class ArticleService {
    private final ImageDao imageDao;
    private final UserDao userDao;
    private final TagDao tagDao;
    private final ImageTagDao imageTagDao;
    private final FavoriteDao favoriteDao;

    @Autowired
    public ArticleService(ImageDao imageDao, UserDao userDao, TagDao tagDao, ImageTagDao imageTagDao, FavoriteDao favoriteDao) {
        this.imageDao = imageDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.imageTagDao = imageTagDao;
        this.favoriteDao = favoriteDao;
    }


    @Transactional
    public String post(Integer userId, ArticlePostForm form) throws IOException {
        String uniqueFileName = ImgUtil.getUniqueFileName(form.getUploadImage().getOriginalFilename());
        var image = new Image();
        image.setUserId(userId);
        image.setImagePath(uniqueFileName);
        image.setExplanation(form.getExplanation());
        imageDao.insert(image);
        form.getTagList().forEach(tag -> {
            Tag insertTag = tagDao.findByTag(tag).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setTag(tag);
                tagDao.insert(newTag);
                return newTag;
            });
            ImageTag imageTag = new ImageTag();
            imageTag.setImageId(image.getImageId());
            imageTag.setTagId(insertTag.getTagId());
            imageTagDao.insert(imageTag);
        });
        return uniqueFileName;
    }
}
