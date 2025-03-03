package com.allmuniz.api_user.service.impl;

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
        User newUser = new User(user);
        userRepository.save(newUser);
        return new  UserResponseDto(newUser.getName(), newUser.getEmail());
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new UserResponseDto(value.getName(), value.getEmail())).orElse(null);
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
            return new UserResponseDto(updateUser.getName(), updateUser.getEmail());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
