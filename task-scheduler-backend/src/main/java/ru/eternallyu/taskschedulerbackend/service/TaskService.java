package ru.eternallyu.taskschedulerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerbackend.entity.Task;
import ru.eternallyu.taskschedulerbackend.mapper.TaskMapper;
import ru.eternallyu.taskschedulerbackend.repository.TaskRepository;
import ru.eternallyu.taskschedulerbackend.service.dto.TaskDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;


    public List<TaskDto> findTasksByEmail(String email) {
        List<Task> tasks = taskRepository.findAllByUserEmail(email);
        return tasks.stream().map(taskMapper::taskToTaskDto).toList();
    }
}
