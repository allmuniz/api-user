package com.allmuniz.api_user.controller;

import com.allmuniz.api_user.domain.dto.UserRequestDto;
import com.allmuniz.api_user.domain.dto.UserRequestUpdateDto;
import com.allmuniz.api_user.domain.dto.UserResponseDto;
import com.allmuniz.api_user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User Manager")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @Operation(description = "Endpoint responsavel por criar um usuário",
            summary = "Criação de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criação efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do usuário"),
            @ApiResponse(responseCode = "403", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping("/{userId}")
    @Operation(description = "Endpoint responsavel por buscar um usuário",
            summary = "Busca de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca do usuário"),
            @ApiResponse(responseCode = "403", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @PutMapping("/update/{userId}")
    @Operation(description = "Endpoint responsavel por atualizar um usuário",
            summary = "Atualização de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na atualização do usuário"),
            @ApiResponse(responseCode = "403", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UserResponseDto> update(@PathVariable Long userId,
                                                  @RequestBody UserRequestUpdateDto user) {
        return ResponseEntity.ok().body(userService.update(user, userId));
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(description = "Endpoint responsavel por deletar um usuário",
            summary = "Deleção de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Remoção efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na remoção do usuário"),
            @ApiResponse(responseCode = "403", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
