package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerbackend.configuration.kafka.KafkaProducerConfig;
import ru.eternallyu.taskschedulerbackend.configuration.kafka.KafkaProperties;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.exception.UserAuthenticationException;
import ru.eternallyu.taskschedulerbackend.service.dto.kafka.EmailSendingDto;
import ru.eternallyu.taskschedulerbackend.service.dto.user.RequestUserDto;
import ru.eternallyu.taskschedulerbackend.util.KafkaUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final KafkaProducerConfig kafkaProducer;

    private final KafkaProperties kafkaProperties;

    public void registerUser(RequestUserDto requestUserDto) {
        String encodedPassword = passwordEncoder.encode(requestUserDto.getPassword());

        User newUser = User.builder()
                .email(requestUserDto.getEmail())
                .password(encodedPassword)
                .build();

        String email = newUser.getEmail();
        String message = KafkaUtils.generateWelcomeMessage(email);
        EmailSendingDto emailSendingDto = EmailSendingDto.builder()
                .to(email)
                .type(kafkaProperties.getWelcomeEmailType())
                .message(message)
                .build();
        kafkaProducer.sendMessage(emailSendingDto);

        userService.createUser(newUser);
    }

    public void loginUser(RequestUserDto requestUserDto) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(requestUserDto.getEmail(), requestUserDto.getPassword());
            authenticationManager.authenticate(authInputToken);
        } catch (AuthenticationException exception) {
            throw new UserAuthenticationException("Invalid email or password");
        }
    }
}
