package ru.eternallyu.taskschedulerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients(basePackages = "ru.eternallyu.taskschedulerservice")
@SpringBootApplication
public class TaskSchedulerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerServiceApplication.class, args);
	}

}
