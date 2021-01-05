package it.univpm.EsameProgrammazione.Utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import it.univpm.EsameProgrammazione.Model.Weather;
import org.springframework.stereotype.Component;


/*
 * Classe che si occupa della gestione del DataSet
 * che servirà per creare le statistiche
 * Il DataSet è sul file esterno "DataSet.json"
 */
@Component
public class DataSet extends TimerTask{
protected Vector<Weather> weathers = new Vector<Weather>();

private Weather primo = new Weather();
private Weather secondo = new Weather();
private Weather terzo = new Weather();
private Weather quarto = new Weather();
private Weather quinto = new Weather();
private Weather sesto = new Weather();
private Weather settimo = new Weather();

public void funzione() {
	LocalDateTime prima = LocalDateTime.of(2020, Month.DECEMBER, 12, 10, 10, 30);
	primo.setData(prima);
	primo.setNomeCitta("Trieste");
	primo.setTemperatura(198.7);
	primo.setUmidita(98);
	weathers.add(primo);
	
	LocalDateTime seconda = LocalDateTime.of(2020, Month.DECEMBER, 30, 10, 10, 30);
	secondo.setData(seconda);
	secondo.setNomeCitta("Genova");
	secondo.setTemperatura(260.7);
	secondo.setUmidita(110);
	weathers.add(secondo);
	
	LocalDateTime terza = LocalDateTime.of(2021, Month.JANUARY, 9, 10, 10, 30);
	terzo.setData(terza);
	terzo.setNomeCitta("Perugia");
	terzo.setTemperatura(260.2);
	terzo.setUmidita(130);
	weathers.add(terzo);
	
	LocalDateTime quarta = LocalDateTime.of(2020, Month.DECEMBER, 7, 10, 10, 30);
	quarto.setData(quarta);
	quarto.setNomeCitta("Potenza");
	quarto.setTemperatura(290.6);
	quarto.setUmidita(130.6);
	weathers.add(quarto);
	
	LocalDateTime quinta = LocalDateTime.of(2021, Month.JANUARY, 12, 10, 10, 30);
	quinto.setData(quinta);
	quinto.setNomeCitta("Firenze");
	quinto.setTemperatura(260);
	quinto.setUmidita(109);
	weathers.add(quinto);
	
	LocalDateTime sesta = LocalDateTime.of(2021, Month.JANUARY, 7, 10, 10, 30);
	sesto.setData(sesta);
	sesto.setNomeCitta("Palermo");
	sesto.setTemperatura(259.2);
	sesto.setUmidita(60);
	weathers.add(sesto);
	
	LocalDateTime settima = LocalDateTime.of(2021, Month.JANUARY, 9, 10, 10, 30);
	settimo.setData(settima);
	settimo.setNomeCitta("Aosta");
	settimo.setTemperatura(250.2);
	settimo.setUmidita(99);
	weathers.add(settimo);

}

	@Override
	public void run() {
		try {
		      File name = new File("dataSet.txt");
		    	  System.out.println("Entrato nell'if prima del salvataggio");
		    	  ObjectOutputStream out =
		    			  new ObjectOutputStream ( new BufferedOutputStream (
		    			  new FileOutputStream (name)));
		    			  out . writeObject ( weathers );
		    			  out . close ();
		    	  System.out.println("Fine salvataggio");
		   }catch(FileNotFoundException e) {
			      e.printStackTrace();
		   }
			catch(IOException e) {
		      e.printStackTrace();
		   }
	}
	
	
	/*
	 * Get and Setter vettore weathers
	 */
	
	public Vector<Weather> getWeathers() {
		return weathers;
	}
	
	public void setWeathers(Vector<Weather> weathers) {
		this.weathers = weathers;
		System.out.println("size di weathers dopo il set: "+weathers.size());
	}
	
	
	/*
	 * Aggiunge oggetto al vettore weathers
	 */
	
	public void addWeather(Weather objectWeather) {
		weathers.add(objectWeather);
		System.out.println(this.weathers.size());
	}
	
	/*
	 * Check se il vettore weathers è vuoto
	 */
	
	public boolean isEmpty() {
		if(this.weathers.isEmpty()) return true;
		else return false;
	}
	
}