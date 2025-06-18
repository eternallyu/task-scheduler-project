package ru.eternallyu.taskschedulerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eternallyu.taskschedulerbackend.service.TaskService;
import ru.eternallyu.taskschedulerbackend.service.dto.TaskDto;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskDto> getUserTasks(Authentication authentication) {
        String email = authentication.getName();
        return taskService.findTasksByEmail(email);
    }
}
