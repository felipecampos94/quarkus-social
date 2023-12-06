package com.api.quarkus.social.model.follower;

import com.api.quarkus.social.model.user.UserResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class FollowerResponse implements Serializable {

    private Long id;

    private UserResponse user;

    private UserResponse follower;
}
