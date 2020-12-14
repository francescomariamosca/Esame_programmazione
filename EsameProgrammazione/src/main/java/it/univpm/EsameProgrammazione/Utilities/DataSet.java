package it.univpm.EsameProgrammazione.Utilities;
import it.univpm.EsameProgrammazione.Model.*;

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
	
	public DataSet() {
		this.weathers = new Vector<Weather>();
	}
	
	public void addWeather(Weather objectWeather) {
		weathers.add(objectWeather);
		weathers.toString();
	}
	
	@Scheduled(fixedRate = 30000)
	public void saveInDataSet() throws IOException
	{
		try {
			FileOutputStream fos = new FileOutputStream("dataSet.txt");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(this.weathers);
		    oos.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
