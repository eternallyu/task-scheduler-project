package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerbackend.service.dto.UserDto;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.exception.AlreadyExistsException;
import ru.eternallyu.taskschedulerbackend.exception.NotFoundException;
import ru.eternallyu.taskschedulerbackend.mapper.UserMapper;
import ru.eternallyu.taskschedulerbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.userToUserDto(user);
    }

    public User createUser(User user) {
        String userEmail = user.getEmail();
        if (userRepository.findByEmail(userEmail).isPresent()) {
            throw new AlreadyExistsException("User already exists");
        }
        return userRepository.save(user);
    }
}
