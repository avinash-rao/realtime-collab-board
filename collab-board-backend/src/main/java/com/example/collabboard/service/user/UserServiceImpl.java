package com.example.collabboard.service.user;

import com.example.collabboard.dto.user.UserRequest;
import com.example.collabboard.dto.user.UserResponse;
import com.example.collabboard.exception.ResourceNotFoundException;
import com.example.collabboard.model.User;
import com.example.collabboard.repository.UserRepository;
import com.example.collabboard.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /* =========================================================
       CREATE USER
     ========================================================= */

    @Override
    public UserResponse createUser(UserRequest request) {

        // Convert DTO → Entity
        User user = MapperUtil.toUser(request);

        User saved = userRepository.save(user);

        return MapperUtil.toUserResponse(saved);
    }


    /* =========================================================
       GET USER BY ID
     ========================================================= */

    @Override
    public UserResponse getUserById(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return MapperUtil.toUserResponse(user);
    }
}
