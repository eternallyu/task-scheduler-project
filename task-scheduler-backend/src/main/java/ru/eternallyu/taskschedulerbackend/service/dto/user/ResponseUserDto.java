package ru.eternallyu.taskschedulerbackend.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {

    private Long id;

    private String email;
}
