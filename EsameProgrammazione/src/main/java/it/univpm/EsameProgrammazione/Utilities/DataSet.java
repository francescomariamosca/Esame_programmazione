package it.univpm.EsameProgrammazione.Utilities;

import it.univpm.EsameProgrammazione.Model.Weather;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Vector;

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

	File name;
	@Scheduled(fixedRate = 30000)
	public void saveInDataSet() throws IOException
	{
		try {
			name = new File("dataSet.txt");
			if (name.exists()) {
				FileOutputStream fos = new FileOutputStream("dataSet.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.weathers);
				oos.close();
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


}
