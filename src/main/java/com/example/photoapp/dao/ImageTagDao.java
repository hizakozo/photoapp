package com.example.photoapp.dao;

import com.example.photoapp.entity.ImageTag;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface ImageTagDao {
    @Insert
    int insert(ImageTag imageTag);
}
