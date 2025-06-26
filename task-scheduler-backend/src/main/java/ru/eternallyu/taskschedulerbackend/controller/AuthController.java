package ru.eternallyu.taskschedulerbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.taskschedulerbackend.service.AuthService;
import ru.eternallyu.taskschedulerbackend.service.dto.user.UserRequestDto;
import ru.eternallyu.taskschedulerbackend.util.JwtUtils;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> registerHandler(@Valid @RequestBody UserRequestDto userRequestDto) {
        authService.registerUser(userRequestDto);
        String JWTToken = JwtUtils.generateToken(userRequestDto.getEmail());
        return Collections.singletonMap("jwt-token", JWTToken);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> loginHandler(@Valid @RequestBody UserRequestDto userRequestDto) {
        authService.loginUser(userRequestDto);
        String JWTToken = JwtUtils.generateToken(userRequestDto.getEmail());
        return Collections.singletonMap("jwt-token", JWTToken);
    }
}
