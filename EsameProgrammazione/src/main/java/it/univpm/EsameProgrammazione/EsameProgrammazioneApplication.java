package it.univpm.EsameProgrammazione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// per scheduling
@SpringBootApplication
@EnableScheduling
public class EsameProgrammazioneApplication{

	public static void main(String[] args) {
		SpringApplication.run(EsameProgrammazioneApplication.class, args);
	}
	

}
