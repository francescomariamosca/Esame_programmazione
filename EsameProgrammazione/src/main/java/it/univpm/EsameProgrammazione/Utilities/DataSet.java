package it.univpm.EsameProgrammazione.Utilities;

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
	
	// private ObjectOutputStream out;
	
	public DataSet() {
		this.weathers = new Vector<Weather>();
		/*try {
			out = new ObjectOutputStream ( new BufferedOutputStream (
					new FileOutputStream ("dataSet.txt", true)));
		}catch(IOException e) {
			e.printStackTrace();
		}*/
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
