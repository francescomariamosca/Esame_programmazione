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


	public Vector <Double> temperatura = new Vector <Double>();
	public Vector <Double> umidita = new Vector <Double>();
	double averageTemperature=0;
	double averageHumidity=0;
	double varianceTemperature=0;
	double varianceHumidity=0;


	/**
	 * Funzione che carica l'array temperatura con i dati relativi alla città inserita.
	 * @param dataSet viene passato il dataset con tutti i dati
	 * @param nomeCitta viene passato il nome della città di cui si vogliono filtrare le statistiche
	 */
	public void caricaArrayTemperatura (DataSet dataSet, String nomeCitta) throws NoDataException{
		for (int i=0; i<dataSet.getWeathers().size();i++)  {
			if (dataSet.getWeathers().get(i).getNomeCitta().equals(nomeCitta)) {
				temperatura.add(dataSet.getWeathers().get(i).getTemperatura());
			}
		}
		if (this.temperatura.isEmpty() || this.temperatura.contains("[]"))
			throw new NoDataException();
	}

	/**
	 * Funzione che carica l'array umidita con i dati relativi alla città inserita.
	 * @param dataSet viene passato il dataset con tutti i dati
	 * @param nomeCitta viene passato il nome della città di cui si vogliono filtrare le statistiche
	 */
	public void caricaArrayUmidita (DataSet dataSet, String nomeCitta) {
		for (int i=0; i< dataSet.getWeathers().size();i++) {
			if (dataSet.getWeathers().get(i).getNomeCitta().equals(nomeCitta)) {
				umidita.add(dataSet.getWeathers().get(i).getUmidita());
			}
		}
	}

	/**
	 * Funzione che popola il JSONObject con i dati relativi alla temperatura massima, minima, media e varianza.
	 * @return ritorna il JSONObject con i dati citati sopra.
	 */
	public JSONObject statsTemperatura() {
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

	/**
	 * Funzione che popola il JSONObject con i dati relativi all' umidità massima, minima, media e varianza.
	 * @return ritorna il JSONObject con i dati citati sopra.
	 */
	public JSONObject statsUmidita () {
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

