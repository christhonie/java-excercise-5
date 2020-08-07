package za.co.idealogic.javaexercise;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import za.co.idealogic.javaexercise.services.CommandLineProcessingService;

@SpringBootApplication
public class JavaExerciseApplication implements CommandLineRunner {

	private final static Logger log = LoggerFactory.getLogger(JavaExerciseApplication.class);

	@Autowired
	CommandLineProcessingService service;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaExerciseApplication.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Java Exercise 4: Application started!");
		
		service.process(args);
        log.info("Java Exercise 4: Application ended!");
	}
   
}
