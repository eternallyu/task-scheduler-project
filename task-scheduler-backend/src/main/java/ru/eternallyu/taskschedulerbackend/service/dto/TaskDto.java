package ru.eternallyu.taskschedulerbackend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private String userEmail;

    private LocalDateTime createdAt;

    private boolean status;

    private LocalDateTime completedAt;
}
