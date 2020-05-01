package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "favorite_history")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteHistoryId;
    private Integer imageId;
    private Integer userId;
    @Column(insertable = false, updatable = false)
    private Timestamp createAt;
}