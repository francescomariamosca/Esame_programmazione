package it.univpm.EsameProgrammazione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 * Run della spring boot application per far partire il programma
 */
@SpringBootApplication
@EnableScheduling
public class EsameProgrammazioneApplication{

	public static void main(String[] args) {
		SpringApplication.run(EsameProgrammazioneApplication.class, args);
	}
	
	

}
