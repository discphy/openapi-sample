package com.discphy.openapi.controller;

import com.discphy.openapi.dto.BaseResponse;
import com.discphy.openapi.dto.UserRequest;
import com.discphy.openapi.dto.UserResponse;
import com.discphy.openapi.entity.User;
import com.discphy.openapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public BaseResponse<List<UserResponse>> findAll() {
        return new BaseResponse<>(userService.findAll());
    }

    @GetMapping("/{id}")
    public BaseResponse<UserResponse> findById(@PathVariable String id) {
        return new BaseResponse<>(userService.findById(id));
    }

    @PostMapping
    public BaseResponse<?> save(@RequestBody UserRequest userRequest) {
        userService.save(userRequest);
        return new BaseResponse<>();
    }

    @PutMapping("/{id}")
    public BaseResponse<?> update(@PathVariable String id, @RequestBody UserRequest userRequest) {
        userService.change(id, userRequest);
        return new BaseResponse<>();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> delete(@PathVariable String id) {
        userService.delete(id);
        return new BaseResponse<>();
    }
}
