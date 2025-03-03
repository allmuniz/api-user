package com.allmuniz.api_user.service.impl;

import com.allmuniz.api_user.controller.exception.UserFoundException;
import com.allmuniz.api_user.controller.exception.UserNotFoundException;
import com.allmuniz.api_user.domain.dto.UserRequestDto;
import com.allmuniz.api_user.domain.dto.UserRequestUpdateDto;
import com.allmuniz.api_user.domain.dto.UserResponseDto;
import com.allmuniz.api_user.domain.model.User;
import com.allmuniz.api_user.domain.repository.UserRepository;
import com.allmuniz.api_user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto create(UserRequestDto user) {
        try {
            User newUser = new User(user);
            userRepository.save(newUser);
            return new  UserResponseDto(newUser.getId(), newUser.getName(), newUser.getEmail());
        }catch(Exception e) {
            throw new UserFoundException("User already created");
        }
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new UserResponseDto(value.getId(), value.getName(), value.getEmail()))
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserResponseDto update(UserRequestUpdateDto user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User updateUser = userOptional.get();
            updateUser.setName(user.name());
            updateUser.setEmail(user.email());
            updateUser.setPassword(user.password());
            userRepository.save(updateUser);
            return new UserResponseDto(updateUser.getId(), updateUser.getName(), updateUser.getEmail());
        }else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(user.getId());
    }
}
