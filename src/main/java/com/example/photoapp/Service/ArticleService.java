package com.example.photoapp.Service;

import com.example.photoapp.dao.*;
import com.example.photoapp.entity.*;
import com.example.photoapp.form.ArticlePostForm;
import com.example.photoapp.response.ArticleListResponse;
import com.example.photoapp.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ImageDao imageDao;
    private final UserDao userDao;
    private final TagDao tagDao;
    private final ImageTagDao imageTagDao;
    private final FavoriteDao favoriteDao;
    private final CommentDao commentDao;

    @Value("${s3.url-template}")
    private String urlTemplate;

    @Autowired
    public ArticleService(ImageDao imageDao, UserDao userDao, TagDao tagDao, ImageTagDao imageTagDao, FavoriteDao favoriteDao, CommentDao commentDao) {
        this.imageDao = imageDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.imageTagDao = imageTagDao;
        this.favoriteDao = favoriteDao;
        this.commentDao = commentDao;
    }

    @Transactional
    public String post(Integer userId, ArticlePostForm form) throws IOException {
        String uniqueFileName = ImgUtil.getUniqueFileName("article/", form.getUploadImage().getOriginalFilename());
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

    public ArticleListResponse list(Integer userId) {
        List<Image> images = imageDao.findByUserIdMyId(userId, userId);

        List<ArticleListResponse.Image> responseImageList = images.stream().map(image -> {
            User user = userDao.findById(image.getUserId()).orElseThrow();
            List<ArticleListResponse.Image.Tag> tags = tagDao.findImageId(image.getImageId()).stream().map(tag ->
                    ArticleListResponse.Image.Tag.builder()
                            .id(tag.getTagId())
                            .tag(tag.getTag()).build()).collect(Collectors.toList());
            return ArticleListResponse.Image.builder()
                    .id(image.getImageId())
                    .userId(image.getUserId())
                    .userName(user.getUserName())
                    .userImagePath(urlTemplate + user.getImagePath())
                    .imagePath(urlTemplate + image.getImagePath())
                    .explanation(image.getExplanation())
                    .favoriteCount(favoriteDao.findFavoriteCountByImageId(image.getImageId()))
                    .commentCount(commentDao.findCommentCountByImageId(image.getImageId()))
                    .postTime(image.getCreateAt().toString())
                    .tagList(tags).build();
        }).collect(Collectors.toList());

        return ArticleListResponse.builder().images(responseImageList).build();
    }
}
