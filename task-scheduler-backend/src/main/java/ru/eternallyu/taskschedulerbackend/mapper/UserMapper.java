package ru.eternallyu.taskschedulerbackend.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.service.dto.UserDto;

@Component
public class UserMapper {

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }
}
