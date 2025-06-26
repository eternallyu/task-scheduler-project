package ru.eternallyu.taskschedulerservice.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.eternallyu.taskschedulerservice.service.dto.task.TaskDto;
import ru.eternallyu.taskschedulerservice.service.dto.user.UserDto;

import java.util.List;

@FeignClient(name = "backend", url = "${backend.base-url}")
public interface BackendClient {

    @GetMapping("/api/users")
    List<UserDto> getAllUsers();

    @GetMapping("/api/users/{id}/tasks")
    List<TaskDto> getTasksByUserId(@PathVariable("id") Long userId);
}
