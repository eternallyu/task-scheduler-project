package ru.eternallyu.taskschedulerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eternallyu.taskschedulerbackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
}
