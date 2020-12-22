package it.univpm.EsameProgrammazione.stats;

import it.univpm.EsameProgrammazione.*;
import it.univpm.EsameProgrammazione.Model.Weather;
import it.univpm.EsameProgrammazione.Utilities.DataSet;


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Vector;
import java.time.LocalDateTime;

import org.springframework.boot.system.SystemProperties;
import org.springframework.web.client.RestTemplate;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class statstemperature {
	String a;

	public Vector <Double> temperatura = new Vector <Double>();
	public Vector <Double> umidita = new Vector <Double>();
	double averageTemperature=0;
	double averageHumidity=0;
	double varianceTemperature=0;
	double varianceHumidity=0;

	@SuppressWarnings("unchecked")
	public void caricaArrayTemperatura (DataSet dataSet, String a) {
		for (int i=0; i<dataSet.getWeathers().size();i++) {
			System.out.println("entrato nel for");
		if (dataSet.getWeathers().get(i).getNomeCitta().equals(a)) {
		 temperatura.add(dataSet.getWeathers().get(i).getTemperatura());
		}
		}
	}	
	
	public void caricaArrayUmidita (DataSet dataSet, String a) {
		for (int i=0; i< dataSet.getWeathers().size();i++) {
			System.out.println("entrato nel for di umidita");
			if (dataSet.getWeathers().get(i).getNomeCitta().equals(a)) {
				umidita.add(dataSet.getWeathers().get(i).getUmidita());
			}
		}
	}
		
	public JSONObject statsTemperatura(String a) {//qual è il problema, il problema è...
		JSONObject object = new JSONObject();
		try {	
			Object maxT = Collections.max(temperatura);
			object.put("Temperatura massima:", maxT);
			Object minT = Collections.min(temperatura);
			object.put("Temperatura minima:", minT);
			
			//average
			for (int i=0; i<temperatura.size(); i++) {
				averageTemperature += temperatura.get(i);
			}
			averageTemperature /= temperatura.size();
			object.put("La media:", averageTemperature);
			
			//variance
			for (int i=0; i<temperatura.size(); i++) {
				varianceTemperature += Math.pow(temperatura.get(i)-averageTemperature ,2);
			}
			
			object.put("La varianza:", varianceTemperature);
			
			}
		 catch (Exception e) {
				System.out.println("Nome non presente, non è possibile ottenere la temperatura");
		}
				return object;
	}
	
	public JSONObject statsUmidita (String a) {
		JSONObject object = new JSONObject();
		try {
			Object maxU = Collections.max(umidita);
			object.put("Umidità massima:", maxU);
			Object minU = Collections.min(umidita);
			object.put("Umidità minima:", minU);
			
			//average
			for (int i=0; i<umidita.size(); i++) {
				averageHumidity += umidita.get(i);
			}
			averageHumidity /= umidita.size();
			object.put("La media per l'umidita:", averageHumidity);
			
			//variance
			for (int i=0; i<umidita.size(); i++) {
				varianceHumidity += Math.pow(umidita.get(i)-averageHumidity ,2);
			}
			
			object.put("La varianza per l'umidita:", varianceHumidity);
		
			
		} catch (Exception e) {
			System.out.println("Nome non presente, riprovare");
		}
		
		return object;
	}
	

}

//domani faccio medie e varianze e metto tutto assieme, ovvero chiamo tutto da una sola funzione 