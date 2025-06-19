package ru.eternallyu.taskschedulerbackend.service.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusDto {

    @NotNull
    private boolean status;
}
