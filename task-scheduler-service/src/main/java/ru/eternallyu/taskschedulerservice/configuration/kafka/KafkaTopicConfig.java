package ru.eternallyu.taskschedulerservice.configuration.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.eternallyu.taskschedulerservice.service.dto.kafka.EmailSendingDto;

import java.util.HashMap;
import java.util.Map;

import static util.KafkaUtils.bootstrapAddress;
import static util.KafkaUtils.kafkaTopicName;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public ProducerFactory<String, EmailSendingDto> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name(kafkaTopicName)
                .partitions(3)
                .replicas(1)
                .configs(Map.of("min.insync.replicas", "1"))
                .build();
    }

    @Bean
    public KafkaTemplate<String, EmailSendingDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
