package it.univpm.EsameProgrammazione.Utilities;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.springframework.scheduling.annotation.Scheduled;

/*
 * Classe che si occupa della gestione del DataSet
 * che servirà per creare le statistiche
 * Il DataSet è sul file esterno "DataSet.json"
 */

public class DataSet {
	public JSONArray arrayWeathers;
	
	public DataSet() {
		JSONArray arrayWeathers = new JSONArray();
	}
	
	public void setArrayWeathers(JSONArray array) {
		this.arrayWeathers = array;
	}
	
	@Scheduled(fixedRate = 30000)
	public void saveInDataSet() {
		try{ 
			FileWriter file = new FileWriter("DataSet.json");
			
			file.write("");
			file.write(this.arrayWeathers.toJSONString());	
			
			file.close();
		}catch(IOException e) {
			System.out.println("Errore di I/O");
		}
	}
	
}
