package ru.eternallyu.taskschedulerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.eternallyu.taskschedulerservice.configuration.kafka.KafkaProducerConfig;
import ru.eternallyu.taskschedulerservice.service.dto.kafka.EmailSendingDto;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduledTaskService {

    private final KafkaProducerConfig kafkaProducer;

    @Scheduled(cron = "${task.cron.expression}")
    public void generateScheduledReport() {
        log.info("Generate scheduled report");
        List<EmailSendingDto> reports = reportGenerator.generateAllReports();
        log.info("Generate scheduled report completed");
        reports.forEach(kafkaProducer::sendMessage);
        log.info("Reports send to kafka");
    }
}
