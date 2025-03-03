package com.allmuniz.api_user.service;

import com.allmuniz.api_user.domain.dto.UserRequestDto;
import com.allmuniz.api_user.domain.dto.UserRequestUpdateDto;
import com.allmuniz.api_user.domain.dto.UserResponseDto;

public interface UserService {

    UserResponseDto create(UserRequestDto user);
    UserResponseDto findById(Long id);
    UserResponseDto update(UserRequestUpdateDto user, Long id);
    void delete(Long id);
}
