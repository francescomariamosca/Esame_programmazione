package it.univpm.EsameProgrammazione.Utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import it.univpm.EsameProgrammazione.Model.Weather;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/*
 * Classe che si occupa della gestione del DataSet
 * che servirà per creare le statistiche
 * Il DataSet è sul file esterno "DataSet.json"
 */
@Component
public class DataSet extends TimerTask{
protected Vector<Weather> weathers = new Vector<Weather>();
	
	/*@Scheduled(fixedRate = 5000)
	public void saveInDataSet() throws IOException
	{
		System.out.println("size di weathers dentro allo Scheduled " + weathers.size() + "     " + n);
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
	}*/
	
	/*
	 * Funzione che si occupa di salvare i dati 
	 * ogni ora dentro al file dataSet.txt
	 */
	
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
		System.out.println("Mese della chiamata effettuata: " + this.weathers.get(1).getData().getDayOfMonth());
	}
	
	/*
	 * Check se il vettore weathers è vuoto
	 */
	
	public boolean isEmpty() {
		if(this.weathers.isEmpty()) return true;
		else return false;
	}
	
}