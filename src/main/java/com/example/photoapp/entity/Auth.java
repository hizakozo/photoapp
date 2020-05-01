package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

@Entity
@Data
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authId;
    private Integer userId;
    private String loginId;
    private String password;
}
