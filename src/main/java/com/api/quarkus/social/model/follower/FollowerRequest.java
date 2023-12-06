package com.api.quarkus.social.model.follower;

import lombok.Data;

import java.io.Serializable;

@Data
public class FollowerRequest implements Serializable {

    private Long followerId;
}
