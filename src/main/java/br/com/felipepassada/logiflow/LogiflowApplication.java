package br.com.felipepassada.logiflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.felipepassada.logiflow.module"})
public class LogiflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogiflowApplication.class, args);
	}

}
