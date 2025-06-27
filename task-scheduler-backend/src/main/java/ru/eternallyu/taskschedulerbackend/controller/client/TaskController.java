package ru.eternallyu.taskschedulerbackend.controller.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.eternallyu.taskschedulerbackend.service.TaskService;
import ru.eternallyu.taskschedulerbackend.service.dto.task.RequestCreateTaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.RequestUpdateTaskStatusDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.ResponseTaskDto;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseTaskDto> getUserTasks(Authentication authentication) {
        String email = authentication.getName();
        return taskService.findTasksByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseTaskDto createTask(Authentication authentication, @Valid @RequestBody RequestCreateTaskDto requestCreateTaskDto) {
        String email = authentication.getName();
        return taskService.createTask(requestCreateTaskDto, email);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTaskDto updateTask(Authentication authentication, @PathVariable Long id, @RequestBody RequestCreateTaskDto requestCreateTaskDto) {
        String email = authentication.getName();
        return taskService.updateTask(id, requestCreateTaskDto, email);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    ResponseTaskDto updateTaskStatus(Authentication authentication, @PathVariable Long id, @Valid @RequestBody RequestUpdateTaskStatusDto requestUpdateTaskStatusDto) {
        String email = authentication.getName();
        return taskService.updateTaskStatus(id, requestUpdateTaskStatusDto.isStatus(), email);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        taskService.deleteTask(id, email);
    }
}
