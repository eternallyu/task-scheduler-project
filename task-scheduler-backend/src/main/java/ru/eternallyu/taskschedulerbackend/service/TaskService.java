package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerbackend.entity.Task;
import ru.eternallyu.taskschedulerbackend.entity.User;
import ru.eternallyu.taskschedulerbackend.exception.NotFoundException;
import ru.eternallyu.taskschedulerbackend.mapper.TaskMapper;
import ru.eternallyu.taskschedulerbackend.repository.TaskRepository;
import ru.eternallyu.taskschedulerbackend.repository.UserRepository;
import ru.eternallyu.taskschedulerbackend.service.dto.task.RequestCreateTaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.ResponseTaskDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TaskMapper taskMapper;


    public List<ResponseTaskDto> findTasksByEmail(String email) {
        List<Task> tasks = taskRepository.findAllByUserEmail(email);
        return tasks.stream().map(taskMapper::taskToTaskDto).toList();
    }

    public ResponseTaskDto createTask(RequestCreateTaskDto requestCreateTaskDto, String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Task task = Task.builder()
                .user(user)
                .title(requestCreateTaskDto.getTitle())
                .description(requestCreateTaskDto.getDescription())
                .build();

        Task saved = taskRepository.save(task);

        return taskMapper.taskToTaskDto(saved);
    }

    public ResponseTaskDto updateTask(Long id, RequestCreateTaskDto dto, String userEmail) {
        Task task = taskRepository
                .findByIdAndUserEmail(id, userEmail)
                .orElseThrow(() ->
                        new NotFoundException("Task with id " + id + " not found"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        Task saved = taskRepository.save(task);

        return taskMapper.taskToTaskDto(saved);
    }

    public ResponseTaskDto updateTaskStatus(Long id, boolean status, String userEmail) {
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

    public List<ResponseTaskDto> findTasksByUserId(Long id) {
        List<Task> tasks = taskRepository.findAllByUserId(id);
        return tasks.stream().map(taskMapper::taskToTaskDto).toList();
    }
}
