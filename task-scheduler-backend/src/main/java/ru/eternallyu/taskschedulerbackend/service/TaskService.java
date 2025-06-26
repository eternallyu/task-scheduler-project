package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerbackend.entity.Task;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.exception.NotFoundException;
import ru.eternallyu.taskschedulerbackend.exception.UserAuthenticationException;
import ru.eternallyu.taskschedulerbackend.mapper.TaskMapper;
import ru.eternallyu.taskschedulerbackend.mapper.UserMapper;
import ru.eternallyu.taskschedulerbackend.repository.TaskRepository;
import ru.eternallyu.taskschedulerbackend.repository.UserRepository;
import ru.eternallyu.taskschedulerbackend.service.dto.task.CreateTaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.TaskDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TaskMapper taskMapper;


    public List<TaskDto> findTasksByEmail(String email) {
        List<Task> tasks = taskRepository.findAllByUserEmail(email);
        return tasks.stream().map(taskMapper::taskToTaskDto).toList();
    }

    public TaskDto createTask(CreateTaskDto createTaskDto, String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Task task = Task.builder()
                .user(user)
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
                .build();

        Task saved = taskRepository.save(task);

        return taskMapper.taskToTaskDto(saved);
    }

    public TaskDto updateTask(Long id, CreateTaskDto dto, String userEmail) {
        Task task = taskRepository
                .findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() ->
                        new NotFoundException("Task with id " + id + " not found"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        Task saved = taskRepository.save(task);

        return taskMapper.taskToTaskDto(saved);
    }

    public TaskDto updateTaskStatus(Long id, boolean status, String userEmail) {
        Task task = taskRepository
                .findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() ->
                        new NotFoundException("Task with id " + id + " not found"));

        task.setStatus(status);
        if (status) {
            task.setCompletedAt(LocalDateTime.now());
        } else {
            task.setCompletedAt(null);
        }
        Task saved = taskRepository.save(task);

        return taskMapper.taskToTaskDto(saved);
    }

    public void deleteTask(Long id, String userEmail) {
        Task task = taskRepository
                .findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() ->
                        new NotFoundException("Task with id " + id + " not found"));

        taskRepository.delete(task);
    }

    public List<TaskDto> findTasksByUserId(Long id) {
        List<Task> tasks = taskRepository.findAllByUserId(id);
        return tasks.stream().map(taskMapper::taskToTaskDto).toList();
    }
}
