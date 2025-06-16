package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.mapper.UserMapper;
import ru.eternallyu.taskschedulerbackend.service.dto.UserDto;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDto userDto = userService.findByEmail(email);
        User user = userMapper.userDtoToUser(userDto);

        return new
                org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_USER")
                )
        );
    }
}
