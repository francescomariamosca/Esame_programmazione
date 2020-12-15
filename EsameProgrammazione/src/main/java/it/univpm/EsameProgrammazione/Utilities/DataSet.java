package it.univpm.EsameProgrammazione.Utilities;
import it.univpm.EsameProgrammazione.Model.*;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

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
	private ObjectOutputStream out;
	
	public DataSet() {
		this.weathers = new Vector<Weather>();
		try {
			out = new ObjectOutputStream ( new BufferedOutputStream (
					new FileOutputStream ("dataSet.txt")));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addWeather(Weather objectWeather) {
		weathers.add(objectWeather);
	}
	
	@Scheduled(fixedRate = 3000)
	public void saveInDataSet() throws IOException
	{
		try {
			out . writeObject ( weathers );
			out . close ();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
