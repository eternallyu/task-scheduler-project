package ru.eternallyu.taskschedulerbackend.service.dto.task;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTaskDto {

    private Long id;

    private String title;

    private String description;

    private String userEmail;

    private LocalDateTime createdAt;

    private boolean status;

    private LocalDateTime completedAt;
}
