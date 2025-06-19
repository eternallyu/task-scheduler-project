package ru.eternallyu.taskschedulerbackend.mapper;

import org.springframework.stereotype.Component;
import ru.eternallyu.taskschedulerbackend.entity.Task;
import ru.eternallyu.taskschedulerbackend.service.dto.task.TaskDto;

@Component
public class TaskMapper {
    public TaskDto taskToTaskDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .completedAt(task.getCompletedAt())
                .createdAt(task.getCreatedAt())
                .description(task.getDescription())
                .status(task.isStatus())
                .title(task.getTitle())
                .userEmail(task.getUser().getEmail())
                .build();
    }

    public Task taskDtoToTask(TaskDto taskDto) {
        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .createdAt(taskDto.getCreatedAt())
                .build();
    }
}
