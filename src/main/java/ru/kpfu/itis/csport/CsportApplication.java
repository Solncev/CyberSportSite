package ru.kpfu.itis.csport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/application-local.properties")
public class CsportApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsportApplication.class, args);
	}
}
