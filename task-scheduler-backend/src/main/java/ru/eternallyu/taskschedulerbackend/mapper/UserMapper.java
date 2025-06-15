package ru.eternallyu.taskschedulerbackend.mapper;

import org.mapstruct.Mapper;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.service.dto.UserDto;

@Mapper
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
