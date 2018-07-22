package br.com.zero.grau.zerograu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.zerograu.domain"})
public class ZerograuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZerograuApplication.class, args);
	}
}
