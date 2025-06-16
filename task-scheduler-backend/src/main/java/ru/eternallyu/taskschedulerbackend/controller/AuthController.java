package ru.eternallyu.taskschedulerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.taskschedulerbackend.service.AuthService;
import ru.eternallyu.taskschedulerbackend.service.dto.UserDto;
import ru.eternallyu.taskschedulerbackend.util.JwtUtils;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> registerHandler(@RequestBody UserDto userDto) {
        authService.registerUser(userDto);
        String JWTToken = jwtUtils.generateToken(userDto.getEmail());
        return Collections.singletonMap("jwt-token", JWTToken);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> loginHandler(@RequestBody UserDto userDto) {
        authService.loginUser(userDto);
        String JWTToken = jwtUtils.generateToken(userDto.getEmail());
        return Collections.singletonMap("jwt-token", JWTToken);
    }
}
