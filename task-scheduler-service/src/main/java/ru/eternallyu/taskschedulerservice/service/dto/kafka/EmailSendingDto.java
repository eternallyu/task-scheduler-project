package ru.eternallyu.taskschedulerservice.service.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendingDto {

    private String to;

    private String type;

    private String message;
}
