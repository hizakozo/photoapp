package com.example.photoapp.dao;

import com.example.photoapp.entity.Tag;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;
import java.util.Optional;

@ConfigAutowireable
@Dao
public interface TagDao {
    @Select
    List<Tag> findImageId(Integer imageId);

    @Select
    Optional<Tag> findByTag(String tag);

    @Select
    Optional<Tag> findById(Integer tagId);

    @Insert
    int insert(Tag tag);
}
