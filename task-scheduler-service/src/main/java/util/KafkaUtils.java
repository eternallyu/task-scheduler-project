package util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

@UtilityClass
public class KafkaUtils {

    @Value(value = "${kafka.topic.name}")
    public static String kafkaTopicName;

    @Value(value = "${kafka.email.type}")
    public static String welcomeEmailType;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    public static String bootstrapAddress;
}