package com.api.quarkus.social.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest implements Serializable {
    @NotBlank(message = "The Name is required.")
    private String name;

    @NotBlank(message = "The Name is required.")
    private Integer age;
}
