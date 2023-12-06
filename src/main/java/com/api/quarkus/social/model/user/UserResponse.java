package com.api.quarkus.social.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse implements Serializable {
    private Long id;
    private String name;
    private Integer age;
}
