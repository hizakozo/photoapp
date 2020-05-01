package com.example.photoapp.dao;

import com.example.photoapp.entity.Auth;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.Optional;

@ConfigAutowireable
@Dao
public interface AuthDao {
    @Select
    Optional<Auth> findByLoginId(String loginId);

    @Insert
    int insert(Auth auth);
}
