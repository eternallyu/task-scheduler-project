package ru.eternallyu.taskschedulerservice.service.dto.kafka;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendingDto {

    private String to;

    private String type;

    private String message;
}
