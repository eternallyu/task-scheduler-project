package ru.eternallyu.taskscheduleremailsender.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.service.dto.kafka.EmailSendingDto;

@Log4j2
@RequiredArgsConstructor
@KafkaListener(topics = "${app.kafka.topic.name}")
@Component
public class EventHandler {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sendFrom;

    @KafkaHandler
    public void handle(EmailSendingDto emailSendingDto) {
        log.info("Received email request: {}", emailSendingDto.getMessage());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sendFrom);
            message.setTo(emailSendingDto.getTo());
            message.setSubject(emailSendingDto.getType());
            message.setText(emailSendingDto.getMessage());

            mailSender.send(message);
            log.info("Email sent successfully: {}", emailSendingDto.getMessage());
        } catch (MailException exception) {
            log.error("Failed to send email: {}", emailSendingDto.getMessage());
        }
    }
}
