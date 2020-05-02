package com.example.photoapp.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleListResponse {
    private List<Image> images;

    @Data
    @Builder
    public static class Image {
        private Integer id;
        private Integer userId;
        private String userName;
        private String userImagePath;
        private String imagePath;
        private String explanation;
        private Integer favoriteCount;
        private Integer commentCount;
        private String postTime;
        private List<Tag> tagList;

        @Data
        @Builder
        public static class Tag {
            private Integer id;
            private String tag;
        }
    }
}