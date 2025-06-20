package ru.eternallyu.taskschedulerbackend.service.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendingDto {

    private String to;

    private String type;

    Map<String, String> parameters;
}
