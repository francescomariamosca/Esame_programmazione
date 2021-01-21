package it.univpm.EsameProgrammazione.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 *
 * Classe che identifica tutti gli oggetti(weather) che raccolgono
 * le informazioni necessarie per la modellazione dati
 */

public class Weather implements Serializable {
	private double temperatura;
	private double umidita;
	private String nomeCitta;
	private LocalDateTime data;
	
	public Weather() {
		super();
	}

	
	/**
	 * Getters and Setters classe Weather
	 */
	
	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public double getUmidita() {
		return umidita;
	}

	public void setUmidita(double umidita) {
		this.umidita = umidita;
	}

	public String getNomeCitta() {
		return nomeCitta;
	}

	public void setNomeCitta(String nomeCitta) {
		this.nomeCitta = nomeCitta;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime now) {
		this.data = now;
	}

	
}
