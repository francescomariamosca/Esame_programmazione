package it.univpm.EsameProgrammazione.Exception;

/*
 * Classe personalizzata Exception Weather
 */

public class WeatherException extends Exception {
	
	public WeatherException() {
		super();
		System.out.println("PROBLEMA DURANTE CREAZIONE CHIAMATA API");
	}
	
}
