package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerbackend.entity.Task;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.mapper.TaskMapper;
import ru.eternallyu.taskschedulerbackend.mapper.UserMapper;
import ru.eternallyu.taskschedulerbackend.repository.TaskRepository;
import ru.eternallyu.taskschedulerbackend.service.dto.UserDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.CreateTaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.TaskDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final TaskMapper taskMapper;

    private final UserMapper userMapper;


    public List<TaskDto> findTasksByEmail(String email) {
        List<Task> tasks = taskRepository.findAllByUserEmail(email);
        return tasks.stream().map(taskMapper::taskToTaskDto).toList();
    }

    public TaskDto createTask(String email, CreateTaskDto createTaskDto) {
        User user = userMapper.userDtoToUser(userService.findUserByEmail(email));
        Task taskToSave = Task.builder()
                .user(user)
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
                .build();
        Task savedTask = taskRepository.save(taskToSave);
        return taskMapper.taskToTaskDto(savedTask);
    }
}
