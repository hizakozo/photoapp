package com.example.photoapp.dao;

import com.example.photoapp.entity.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface UserDao {
    @Select
    Optional<User> findById(Integer userId);

    @Insert
    int insert(User user);
}
