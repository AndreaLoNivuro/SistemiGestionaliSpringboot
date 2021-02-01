package it.jac.sistemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "it.jac.sistemi")
public class SistemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemiApplication.class, args);
	}

}
