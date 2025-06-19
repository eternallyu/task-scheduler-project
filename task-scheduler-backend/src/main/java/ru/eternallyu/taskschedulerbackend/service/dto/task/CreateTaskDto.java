package ru.eternallyu.taskschedulerbackend.service.dto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class CreateTaskDto {

    @NotBlank(message = "Title is blank")
    private String title;

    private String description;
}
