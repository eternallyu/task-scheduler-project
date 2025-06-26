package ru.eternallyu.taskschedulerbackend.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.service.dto.user.UserRequestDto;
import ru.eternallyu.taskschedulerbackend.service.dto.user.UserResponseDto;

@Component
public class UserMapper {

    public UserRequestDto userToUserRequestDto(User user) {
        return UserRequestDto.builder()
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public User userDtoToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .password(userRequestDto.getPassword())
                .email(userRequestDto.getEmail())
                .build();
    }
}
