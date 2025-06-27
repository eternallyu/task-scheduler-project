package ru.eternallyu.taskschedulerbackend.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.service.dto.user.RequestUserDto;
import ru.eternallyu.taskschedulerbackend.service.dto.user.ResponseUserDto;

@Component
public class UserMapper {

    public RequestUserDto userToUserRequestDto(User user) {
        return RequestUserDto.builder()
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public ResponseUserDto userToUserResponseDto(User user) {
        return ResponseUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public User userDtoToUser(RequestUserDto requestUserDto) {
        return User.builder()
                .password(requestUserDto.getPassword())
                .email(requestUserDto.getEmail())
                .build();
    }
}
