package com.example.photoapp.entity;

import lombok.Data;
import org.seasar.doma.*;

@Entity
@Data
@Table(name = "friend_status")
public class FriendStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friendStatusId;
    private Integer followUserId;
    private Integer followerUserId;
}
