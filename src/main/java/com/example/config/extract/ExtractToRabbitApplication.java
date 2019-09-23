package com.example.config.extract;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ExtractToRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtractToRabbitApplication.class, args);
	}

}
