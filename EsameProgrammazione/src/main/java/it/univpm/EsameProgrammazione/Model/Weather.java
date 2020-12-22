package it.univpm.EsameProgrammazione.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 *
 * Classe che rappresenta una chiamata e raccoglie
 * le informazioni necessarie per le statistiche
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
