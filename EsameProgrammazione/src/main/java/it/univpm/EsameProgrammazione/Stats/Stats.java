package it.univpm.EsameProgrammazione.Stats;

import it.univpm.EsameProgrammazione.Exception.NoDataException;
import it.univpm.EsameProgrammazione.Utilities.DataSet;

import java.util.Collections;
import java.util.Vector;
import org.json.simple.JSONObject;


/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 * Classe che si occupa della gestione delle statistiche
 */
public class Stats {
	String a;

	public Vector <Double> temperatura = new Vector <Double>();
	public Vector <Double> umidita = new Vector <Double>();
	double averageTemperature=0;
	double averageHumidity=0;
	double varianceTemperature=0;
	double varianceHumidity=0;

	@SuppressWarnings("unchecked")
	public void caricaArrayTemperatura (DataSet dataSet, String a) throws NoDataException{
		for (int i=0; i<dataSet.getWeathers().size();i++)  {
			if (dataSet.getWeathers().get(i).getNomeCitta().equals(a)) {
				temperatura.add(dataSet.getWeathers().get(i).getTemperatura());
			}
		}
		if (this.temperatura.isEmpty() || this.temperatura.contains("[]"))
			throw new NoDataException();
	}	
	
	public void caricaArrayUmidita (DataSet dataSet, String a) {
		for (int i=0; i< dataSet.getWeathers().size();i++) {
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

		}catch(Exception e) {
			object.put("errore messaggio", e.getMessage());
			object.put("errore causa", e.getCause());
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
		}catch(Exception e) {
			object.put("errore", e.getMessage());
			object.put("errore", e.getCause());
		}
		
		return object;
	}
	

}

