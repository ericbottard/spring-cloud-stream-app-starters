package org.springframework.cloud.stream.app.jdbc.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcSinkKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcSinkKafkaApplication.class, args);
	}
}
