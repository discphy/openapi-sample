package com.discphy.openapi.dto;

import com.discphy.openapi.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private int age;

    @Builder
    public UserResponse(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .age(user.getAge())
                .build();
    }
}
