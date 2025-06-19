package ru.eternallyu.taskschedulerbackend.service.dto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {

    @NotBlank(message = "Title is blank")
    private String title;

    private String description;
}
