package ru.eternallyu.taskschedulerbackend.configuration.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class KafkaProperties {

    @Value(value = "${app.kafka.topic.name}")
    private String kafkaTopicName;

    @Value(value = "${app.kafka.email.type}")
    private String welcomeEmailType;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
}
