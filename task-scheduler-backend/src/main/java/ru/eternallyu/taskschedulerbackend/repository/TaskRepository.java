package ru.eternallyu.taskschedulerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eternallyu.taskschedulerbackend.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserEmail(String email);

    Optional<Task> findByIdAndUserEmail(Long id,String email);
}
