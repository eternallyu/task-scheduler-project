package ru.eternallyu.taskschedulerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eternallyu.taskschedulerbackend.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserEmail(String email);
}
