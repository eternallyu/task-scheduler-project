package ru.eternallyu.taskschedulerbackend.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.entity.Task;
import ru.eternallyu.taskschedulerbackend.service.dto.task.ResponseTaskDto;

@Component
public class TaskMapper {
    public ResponseTaskDto taskToTaskDto(Task task) {
        return ResponseTaskDto.builder()
                .id(task.getId())
                .completedAt(task.getCompletedAt())
                .createdAt(task.getCreatedAt())
                .description(task.getDescription())
                .status(task.isStatus())
                .title(task.getTitle())
                .userEmail(task.getUser().getEmail())
                .build();
    }
}
