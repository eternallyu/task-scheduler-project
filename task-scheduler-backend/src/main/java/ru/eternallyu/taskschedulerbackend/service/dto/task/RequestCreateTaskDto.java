package ru.eternallyu.taskschedulerbackend.service.dto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class RequestCreateTaskDto {

    @NotBlank(message = "Title is blank")
    private String title;

    private String description;
}
