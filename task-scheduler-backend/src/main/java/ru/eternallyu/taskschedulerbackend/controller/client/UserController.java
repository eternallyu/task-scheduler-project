package ru.eternallyu.taskschedulerbackend.controller.client;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eternallyu.taskschedulerbackend.service.UserService;
import ru.eternallyu.taskschedulerbackend.service.dto.user.RequestUserDto;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public RequestUserDto getUserDetails(Authentication authentication) {
        String email = authentication.getName();
        return userService.findUserByEmail(email);
    }
}
