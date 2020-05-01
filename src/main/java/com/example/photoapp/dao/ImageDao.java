package com.example.photoapp.dao;

import com.example.photoapp.entity.Favorite;
import com.example.photoapp.entity.Image;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface ImageDao {
    @Select
    List<Image> findByUserIdMyId(Integer userId, Integer myId);

    @Select
    List<Image> findByUserId(Integer userId);

    @Select
    List<Image> findByTagId(Integer tagId);

    @Select
    List<Image> findByFavorite(Integer userId);

    @Select
    Image findByImageId(Integer imageId);

    @Insert
    int insert(Image image);

    @Insert
    int insert(Favorite favorite);
}
