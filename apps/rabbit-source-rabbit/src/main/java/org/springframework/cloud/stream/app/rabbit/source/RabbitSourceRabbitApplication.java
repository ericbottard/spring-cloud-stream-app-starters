package org.springframework.cloud.stream.app.rabbit.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitSourceRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitSourceRabbitApplication.class, args);
	}
}
