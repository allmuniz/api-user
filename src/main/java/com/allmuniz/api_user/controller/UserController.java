package com.allmuniz.api_user.controller;

import com.allmuniz.api_user.domain.dto.UserRequestDto;
import com.allmuniz.api_user.domain.dto.UserRequestUpdateDto;
import com.allmuniz.api_user.domain.dto.UserResponseDto;
import com.allmuniz.api_user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long userId,
                                                  @RequestBody UserRequestUpdateDto user) {
        return ResponseEntity.ok().body(userService.update(user, userId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
