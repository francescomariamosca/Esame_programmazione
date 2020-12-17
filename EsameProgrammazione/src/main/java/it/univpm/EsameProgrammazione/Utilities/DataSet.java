package it.univpm.EsameProgrammazione.Utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class DataSet{
	protected Vector<Weather> weathers;
	protected Weather weather = new Weather(0,0,0,"");
	
	public DataSet() {
		this.weathers = new Vector<Weather>();
	}
	
	public void addWeather(Weather objectWeather) {
		weathers.add(objectWeather);


		weathers.toString();
	}
	
	
	File name;
	@Scheduled(fixedRate = 30000)
	public void saveInDataSet() throws IOException
	{
	   try {
	      name = new File("dataSet.txt");
	      if (name.exists()) {
	    	  System.out.println("Prova");
	    	  ObjectOutputStream out =
	    			  new ObjectOutputStream ( new BufferedOutputStream (
	    			  new FileOutputStream (name)));
	    			  out . writeObject ( weather );
	    			  out . close ();
	      }
	   }
	   catch(FileNotFoundException e) {
	      e.printStackTrace();
	   }
	   catch(IOException e) {
	      e.printStackTrace();
	   }
	}

	public Vector<Weather> getWeathers() {
		return weathers;
	}

	public void setWeathers(Vector<Weather> weathers) {
		this.weathers = weathers;
	}
	
	public boolean isEmpty() {
		if(this.weathers.isEmpty()) return true;
		else return false;
	}

	public Weather getWeather() {
		return weather;
	}
	// Birichino
	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public File getName() {
		return name;
	}

	public void setName(File name) {
		this.name = name;
	}


}
