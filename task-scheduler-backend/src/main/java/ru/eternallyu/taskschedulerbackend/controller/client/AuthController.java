package ru.eternallyu.taskschedulerbackend.controller.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.taskschedulerbackend.service.AuthService;
import ru.eternallyu.taskschedulerbackend.service.dto.user.RequestUserDto;
import ru.eternallyu.taskschedulerbackend.util.JwtUtils;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${jwt.secret}")
    private String secret;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> registerHandler(@Valid @RequestBody RequestUserDto requestUserDto) {
        authService.registerUser(requestUserDto);
        String JWTToken = JwtUtils.generateToken(requestUserDto.getEmail(), secret);
        return Collections.singletonMap("jwt-token", JWTToken);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> loginHandler(@Valid @RequestBody RequestUserDto requestUserDto) {
        authService.loginUser(requestUserDto);
        String JWTToken = JwtUtils.generateToken(requestUserDto.getEmail(), secret);
        return Collections.singletonMap("jwt-token", JWTToken);
    }
}
