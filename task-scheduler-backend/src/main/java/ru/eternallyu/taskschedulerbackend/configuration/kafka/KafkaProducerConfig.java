package ru.eternallyu.taskschedulerbackend.configuration.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import ru.eternallyu.taskschedulerbackend.service.dto.kafka.EmailSendingDto;

import java.util.concurrent.CompletableFuture;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    private final KafkaTemplate<String, EmailSendingDto> kafkaTemplate;

    public void sendMessage(EmailSendingDto emailSendingDto) {
        CompletableFuture<SendResult<String, EmailSendingDto>> future = kafkaTemplate.send(kafkaProperties.getKafkaTopicName(), emailSendingDto);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + emailSendingDto +
                                   "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                                   emailSendingDto + "] due to : " + ex.getMessage());
            }
        });
    }
}
