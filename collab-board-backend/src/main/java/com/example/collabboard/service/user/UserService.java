package com.example.collabboard.service.user;

import com.example.collabboard.dto.user.UserRequest;
import com.example.collabboard.dto.user.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);
    UserResponse getUserById(String userId);
}
