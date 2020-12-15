package it.univpm.EsameProgrammazione.Model;

/*
 * Classe che rappresenta il tempo di una chiamata
 * con le informazioni richieste
 */

import java.io.Serializable;

public class Weather implements Serializable { // per serializzazione
	private double temperatura;
	private double umidita;
	private String nomeCitta;
	private String data;
	
	public Weather(double temp, double umi, int zip, String citta) {
		super();
	}

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
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	
}
