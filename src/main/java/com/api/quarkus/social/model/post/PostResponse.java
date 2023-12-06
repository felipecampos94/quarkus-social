package com.api.quarkus.social.model.post;

import com.api.quarkus.social.model.user.UserResponse;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PostResponse implements Serializable {

    private Long id;

    private String text;

    private LocalDateTime dateTime;
}
