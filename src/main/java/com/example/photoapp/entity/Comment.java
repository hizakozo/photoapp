package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;
    private Integer image_id;
    private Integer user_id;
    private String comment;
    @Column(insertable = false, updatable = false)
    private Timestamp create_at;
}