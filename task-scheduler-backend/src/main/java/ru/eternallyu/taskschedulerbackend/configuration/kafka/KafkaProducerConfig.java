package ru.eternallyu.taskschedulerbackend.configuration.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import ru.eternallyu.taskschedulerbackend.service.dto.kafka.EmailSendingDto;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaTemplate<String, EmailSendingDto> kafkaTemplate;

    private static final String WELCOME_EMAIL_TYPE = "WELCOME";


    public void sendMessage(String to) {
        EmailSendingDto emailSendingRequestDto = EmailSendingDto.builder()
                .to(to)
                .type(WELCOME_EMAIL_TYPE)
                .parameters(new HashMap<>())
                .build();
        CompletableFuture<SendResult<String, EmailSendingDto>> future = kafkaTemplate.send(KafkaTopicConfig.KAFKA_TOPIC_NAME, emailSendingRequestDto);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + emailSendingRequestDto +
                                   "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                                   emailSendingRequestDto + "] due to : " + ex.getMessage());
            }
        });
    }
}
