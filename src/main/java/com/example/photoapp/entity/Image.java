package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    private Integer userId;
    private String imagePath;
    private String explanation;
    @Column(insertable = false, updatable = false)
    private Timestamp createAt;
}
