package com.discphy.openapi.dto;

import com.discphy.openapi.entity.User;
import lombok.Data;

@Data
public class UserRequest {

    private String username;
    private int age;

    public User toEntity() {
        return new User(username, age);
    }
}
