package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

@Entity
@Data
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;
    private String tag;
}
