package ru.eternallyu.taskschedulerbackend.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaUtils {

    public String generateWelcomeMessage(String email) {
        return "Welcome to " + email + "!";
    }

}
