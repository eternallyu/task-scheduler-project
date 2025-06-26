package ru.eternallyu.taskschedulerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class TaskSchedulerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerServiceApplication.class, args);
	}

}
