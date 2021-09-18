package ru.sugarisboy.homecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.sugarisboy.homecore")
public class HomeCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeCoreApplication.class, args);
	}

}
