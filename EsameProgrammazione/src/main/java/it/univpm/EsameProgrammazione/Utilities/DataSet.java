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


/**
 * Classe che si occupa della gestione del DataSet
 * Il DataSet è sul file esterno "dataSet.txt"
 */
@Component
public class DataSet extends TimerTask{
protected Vector<Weather> weathers = new Vector<Weather>(); 

	@Override
	public void run() {
		try {
		      File name = new File("dataSet.txt");
		    	  ObjectOutputStream out =
		    			  new ObjectOutputStream ( new BufferedOutputStream (
		    			  new FileOutputStream (name)));
		    			  out . writeObject ( weathers );
		    			  out . close ();
		    	  System.out.println("Salvataggio effettuato");
		   }catch(FileNotFoundException e) {
			      e.printStackTrace();
		   }
			catch(IOException e) {
		      e.printStackTrace();
		   }
	}
	
	
	/**
	 * Getter and Setter vettore weathers
	 */
	
	public Vector<Weather> getWeathers() {
		return weathers;
	}
	
	public void setWeathers(Vector<Weather> weathers) {
		this.weathers = weathers;
	}
	
	
	/**
	 * Aggiunge oggetto al vettore weathers
	 */
	
	public void addWeather(Weather objectWeather) {
		weathers.add(objectWeather);
	}
	
	/**
	 * Check se il vettore weathers è vuoto.
	 */
	
	public boolean isEmpty() {
		if(this.weathers.isEmpty()) return true;
		else return false;
	}
	
}