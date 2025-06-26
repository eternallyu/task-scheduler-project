package ru.eternallyu.taskscheduleremailsender.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.service.dto.kafka.EmailSendingDto;

@Component
@KafkaListener(topics = "${app.kafka.topic.name}")
@Log4j2
public class EventHandler {

    @KafkaHandler
    public void handle(EmailSendingDto emailSendingDto) {
        log.info(emailSendingDto.getMessage());
    }
}
