package com.example.photoapp.dao;

import com.example.photoapp.entity.Comment;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface CommentDao {
    @Select
    List<Comment> findByImageId(Integer imageId);

    @Select
    Integer findCommentCountByImageId(Integer imageId);

    @Insert
    int insert(Comment comment);
}
