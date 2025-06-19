package ru.eternallyu.taskschedulerbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.taskschedulerbackend.service.TaskService;
import ru.eternallyu.taskschedulerbackend.service.dto.task.CreateTaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.TaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.UpdateStatusDto;

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
    public TaskDto createTask(Authentication authentication, @Valid @RequestBody CreateTaskDto createTaskDto) {
        String email = authentication.getName();
        return taskService.createTask(createTaskDto, email);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(Authentication authentication, @PathVariable Long id, @RequestBody CreateTaskDto createTaskDto) {
        String email = authentication.getName();
        return taskService.updateTask(id, createTaskDto, email);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    TaskDto updateTaskStatus(Authentication authentication, @PathVariable Long id, @Valid @RequestBody UpdateStatusDto updateStatusDto) {
        String email = authentication.getName();
        return taskService.updateTaskStatus(id, updateStatusDto.isStatus(), email);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        taskService.deleteTask(id, email);
    }
}
