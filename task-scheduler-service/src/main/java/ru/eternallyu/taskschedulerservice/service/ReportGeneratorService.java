package ru.eternallyu.taskschedulerservice.service;

import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerservice.service.dto.kafka.EmailSendingDto;

import java.util.List;

@Service
public class ReportGeneratorService {

    public List<EmailSendingDto> generateAllReports() {
        return null;
    }
}
