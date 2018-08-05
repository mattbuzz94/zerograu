package br.com.zerograu;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ZerograuApplication {
	//private static Logger logger = LogManager.getLogger(ZerograuApplication.class);
	public static void main(String[] args) {
	//	logger.info("Starting Spring Boot application..");
		SpringApplication.run(ZerograuApplication.class, args);
	}
}