package br.com.zerograu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ZerograuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZerograuApplication.class, args);
	}
}
