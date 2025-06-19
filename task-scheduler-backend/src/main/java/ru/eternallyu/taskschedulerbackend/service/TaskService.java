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
import ru.eternallyu.taskschedulerbackend.service.dto.task.CreateTaskDto;
import ru.eternallyu.taskschedulerbackend.service.dto.task.TaskDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public TaskDto createTask(CreateTaskDto createTaskDto, String email) {
        User user = userMapper.userDtoToUser(userService.findUserByEmail(email));
        Task taskToSave = Task.builder()
                .user(user)
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
                .build();
        Task savedTask = taskRepository.save(taskToSave);
        return taskMapper.taskToTaskDto(savedTask);
    }

    public TaskDto updateTask(Long id, CreateTaskDto createTaskDto, String userEmail) {
        Optional<Task> mayBeTask = taskRepository.findById(id);
        if (mayBeTask.isPresent()) {
            Task taskToUpdate = mayBeTask.get();
            if (taskToUpdate.getUser().getEmail().equals(userEmail)) {
                taskToUpdate.setTitle(createTaskDto.getTitle());
                taskToUpdate.setDescription(createTaskDto.getDescription());
                return taskMapper.taskToTaskDto(taskRepository.save(taskToUpdate));
            } else {
                throw new UserAuthenticationException("This is another user's task");
            }
        } else {
            throw new NotFoundException("Task with id " + id + " not found");
        }
    }

    public TaskDto updateTaskStatus(Long id, boolean status, String userEmail) {
        Optional<Task> mayBeTask = taskRepository.findById(id);
        if (mayBeTask.isPresent()) {
            Task taskToUpdate = mayBeTask.get();
            if (taskToUpdate.getUser().getEmail().equals(userEmail)) {
                taskToUpdate.setStatus(status);
                if (status) {
                    taskToUpdate.setCompletedAt(LocalDateTime.now());
                } else {
                    taskToUpdate.setCompletedAt(null);
                }
                return taskMapper.taskToTaskDto(taskRepository.save(taskToUpdate));
            } else {
                throw new UserAuthenticationException("This is another user's task");
            }
        } else {
            throw new NotFoundException("Task with id " + id + " not found");
        }
    }

    public void deleteTask(Long id, String userEmail) {
        Optional<Task> mayBeTask = taskRepository.findById(id);
        if (mayBeTask.isPresent()) {
            Task taskToDelete = mayBeTask.get();
            if (taskToDelete.getUser().getEmail().equals(userEmail)) {
                taskRepository.delete(taskToDelete);
            } else {
                throw new UserAuthenticationException("This is another user's task");
            }
        } else {
            throw new NotFoundException("Task with id " + id + " not found");
        }
    }
}
