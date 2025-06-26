package ru.eternallyu.taskschedulerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerservice.configuration.kafka.KafkaProperties;
import ru.eternallyu.taskschedulerservice.http.BackendClient;
import ru.eternallyu.taskschedulerservice.service.dto.kafka.EmailSendingDto;
import ru.eternallyu.taskschedulerservice.service.dto.task.TaskDto;
import ru.eternallyu.taskschedulerservice.service.dto.user.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReportGeneratorService {

    private final KafkaProperties kafkaProperties;

    private final BackendClient backendClient;

    public List<EmailSendingDto> generateAllReports() {
        List<EmailSendingDto> resultList = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dayAgo = now.minusDays(1);

        List<UserDto> users;
        try {
            users = backendClient.getAllUsers();
        } catch (Exception exception) {
            log.error("Error getting user list");
            return resultList;
        }

        for (UserDto user : users) {
            List<TaskDto> userTasks;
            try {
                userTasks = backendClient.getTasksByUserId(user.getId());
            } catch (Exception e) {
                log.error("Error getting user tasks list");
                continue;
            }

            String message = getMessage(userTasks, dayAgo);

            EmailSendingDto emailSendingDto = EmailSendingDto.builder()
                    .to(user.getEmail())
                    .type(kafkaProperties.getReportEmailType())
                    .message(message)
                    .build();

            resultList.add(emailSendingDto);
        }

        return resultList;
    }

    private static String getMessage(List<TaskDto> userTasks, LocalDateTime dayAgo) {
        boolean thereAreUnfulfilledTasks = false;
        int countUnfulfilledTasks = 0;

        boolean thereAreCompletedTasks = false;
        int countCompletedTasks = 0;

        for (TaskDto task : userTasks) {

            if (!task.isStatus()) {
                thereAreUnfulfilledTasks = true;
                countUnfulfilledTasks++;
            }
            if (task.isStatus() && task.getCompletedAt() != null && task.getCompletedAt().isAfter(dayAgo)) {
                thereAreCompletedTasks = true;
                countCompletedTasks++;
            }
        }

        String message;
        if (thereAreUnfulfilledTasks && thereAreCompletedTasks) {
            message = String.format("У вас %d несделанных задач и %d сделанных задач за последние 24ч", countUnfulfilledTasks, countCompletedTasks);
        } else if (thereAreUnfulfilledTasks) {
            message = String.format("У вас осталось %d несделанных задач", countUnfulfilledTasks);
        } else if (thereAreCompletedTasks) {
            message = String.format("За сегодня вы выполнили %d задач", countCompletedTasks);
        } else {
            message = "У вас пока нет задач";
        }
        return message;
    }
}
