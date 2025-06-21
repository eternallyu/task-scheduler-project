package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerbackend.configuration.kafka.KafkaProducerConfig;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.exception.UserAuthenticationException;
import ru.eternallyu.taskschedulerbackend.service.dto.UserDto;
import ru.eternallyu.taskschedulerbackend.service.dto.kafka.EmailSendingDto;
import ru.eternallyu.taskschedulerbackend.util.KafkaUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final KafkaProducerConfig kafkaProducer;

    public void registerUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User newUser = User.builder()
                .email(userDto.getEmail())
                .password(encodedPassword)
                .build();

        String email = newUser.getEmail();
        String message = KafkaUtils.generateWelcomeMessage(email);
        EmailSendingDto emailSendingDto = EmailSendingDto.builder()
                .to(email)
                .type(KafkaUtils.welcomeEmailType)
                .message(message)
                .build();
        kafkaProducer.sendMessage(emailSendingDto);

        userService.createUser(newUser);
    }

    public void loginUser(UserDto userDto) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
            authenticationManager.authenticate(authInputToken);
        } catch (AuthenticationException exception) {
            throw new UserAuthenticationException("Invalid email or password");
        }
    }
}
