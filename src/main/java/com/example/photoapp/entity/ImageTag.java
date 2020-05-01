package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

@Entity
@Data
@Table(name = "image_tag")
public class ImageTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageTagId;
    private Integer imageId;
    private Integer tagId;
}
