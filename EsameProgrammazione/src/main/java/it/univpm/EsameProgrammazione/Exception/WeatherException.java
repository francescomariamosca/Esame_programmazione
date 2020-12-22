package it.univpm.EsameProgrammazione.Exception;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 * Classe personalizzata Exception Weather
 */

public class WeatherException extends Exception {
	
	public WeatherException() {
		super();
		System.out.println("PROBLEMA DURANTE CREAZIONE CHIAMATA API");
	}
	
}
