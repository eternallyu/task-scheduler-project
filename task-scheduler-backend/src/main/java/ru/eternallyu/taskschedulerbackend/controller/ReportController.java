package ru.eternallyu.taskschedulerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eternallyu.taskschedulerbackend.service.TaskService;
import ru.eternallyu.taskschedulerbackend.service.UserService;
import ru.eternallyu.taskschedulerbackend.service.dto.user.UserRequestDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.TaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.user.UserResponseDto;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class ReportController {

    private final UserService userService;

    private final TaskService taskService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("{id}/tasks")
    public List<TaskDto> findTaskByUserId(@PathVariable Long id) {
        return taskService.findTasksByUserId(id);
    }
}
