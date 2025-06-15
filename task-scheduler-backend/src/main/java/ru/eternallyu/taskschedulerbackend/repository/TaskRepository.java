package ru.eternallyu.taskschedulerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eternallyu.taskschedulerbackend.entity.Task;
import ru.eternallyu.taskschedulerbackend.entity.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);
}
