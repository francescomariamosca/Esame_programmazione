package Model;

public class Weather {
	private double temperatura;
	private double umidita;
	private String nomeCitta;
	
	public Weather(double temp, double umi, int zip, String citta) {
		super();
		this.nomeCitta = citta;
		this.temperatura = temp;
		this.umidita = umi;
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
	

	
}
