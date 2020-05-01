package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String imagePath;
}
