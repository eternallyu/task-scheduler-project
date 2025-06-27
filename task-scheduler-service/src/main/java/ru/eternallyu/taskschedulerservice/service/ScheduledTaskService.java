package ru.eternallyu.taskschedulerservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerservice.configuration.kafka.KafkaProducerConfig;
import ru.eternallyu.taskschedulerservice.service.dto.kafka.EmailSendingDto;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ScheduledTaskService {

    private final ReportGeneratorService reportGenerator;

    private final KafkaProducerConfig kafkaProducer;

    @Scheduled(cron = "${task.cron.expression}")
    public void generateScheduledReport() {
        log.info("Generating scheduled report");
        List<EmailSendingDto> reports = reportGenerator.generateAllReports();

        log.info("Sending messages to kafka");
        reports.forEach(kafkaProducer::sendMessage);
    }
}
