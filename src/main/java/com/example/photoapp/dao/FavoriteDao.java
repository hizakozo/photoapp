package com.example.photoapp.dao;

import com.example.photoapp.entity.Favorite;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface FavoriteDao {
    @Select
    List<Favorite> findByImageId(Integer imageId);

    @Select
    Integer findFavoriteCountByImageId(Integer imageId);

    @Insert
    int insert(Favorite favorite);
}
