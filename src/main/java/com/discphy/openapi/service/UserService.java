package com.discphy.openapi.service;

import com.discphy.openapi.dto.UserRequest;
import com.discphy.openapi.dto.UserResponse;
import com.discphy.openapi.entity.User;
import com.discphy.openapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(UserResponse::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse findById(String id) {
        return UserResponse.of(userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Not found user")));
    }

    public void save(UserRequest userRequest) {
        userRepository.save(userRequest.toEntity());
    }

    public void change(String id, UserRequest userRequest) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Not exist User"));

        if (userRequest.getAge() != 0) {
            user.setAge(userRequest.getAge());
        }

        if (hasText(userRequest.getUsername())) {
            user.setUsername(userRequest.getUsername());
        }
    }

    public void delete(String id) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Not exist User"));

        userRepository.delete(user);
    }
}
