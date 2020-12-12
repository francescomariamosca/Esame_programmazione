package Model;

public class Weather {
	private float temperatura;
	private float umidita;
	private int zipCode;
	private String nomeCitta;
	
	public Weather(float temp, float umi, int zip, String citta) {
		super();
		this.nomeCitta = citta;
		this.temperatura = temp;
		this.umidita = umi;
		this.zipCode = zip;
	}
	
	/* Getter */
	public float getTemperatura() {
		return this.temperatura;
	}
	public int getZipCode() {
		return this.zipCode;
	}
	public String getNomeCitta() {
		return this.nomeCitta;
	}
	public float getUmidita() {
		return this.umidita;
	}
	
}
