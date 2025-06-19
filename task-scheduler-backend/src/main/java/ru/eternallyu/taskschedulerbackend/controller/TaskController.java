package ru.eternallyu.taskschedulerbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.taskschedulerbackend.service.TaskService;
import ru.eternallyu.taskschedulerbackend.service.dto.task.CreateTaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.TaskDto;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getUserTasks(Authentication authentication) {
        String email = authentication.getName();
        return taskService.findTasksByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@Valid @RequestBody CreateTaskDto createTaskDto, Authentication authentication) {
        String email = authentication.getName();
        return taskService.createTask(email, createTaskDto);
    }
}
