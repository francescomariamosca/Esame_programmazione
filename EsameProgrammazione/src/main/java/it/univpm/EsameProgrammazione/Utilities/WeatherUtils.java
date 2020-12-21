package it.univpm.EsameProgrammazione.Utilities;

import it.univpm.EsameProgrammazione.Model.Weather;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;    
import org.springframework.web.client.RestTemplate;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * Classe che realizza le chiamate API
 * il parsing in JSON e popola il vettore di arrayJSON
 * che compone il DataSet
 */


public class WeatherUtils implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String data;
	protected JSONObject objectJ;
	//protected JSONArray weathers;
	protected JSONObject main;
	protected JSONParser parser;
	protected JSONObject obj;
	
	// protected DataSet dataSetArray;
	public Weather weather;
	
	public WeatherUtils() {
		this.data = "";
		this.objectJ = new JSONObject();
		this.main = new JSONObject();
		this.parser = new JSONParser();
		this.obj = new JSONObject();
	}
	
	/*
	 * Funzione che realizza la chiamata API, effettua il parsing
	 * e popola il vettore di array JSON
	 * // ok
	 */
	
	public JSONObject chiamataAPI(String zipCode, DataSet dataSet) throws ParseException {
		String url = "http://api.openweathermap.org/data/2.5/weather?zip="+zipCode+",it&appid=d35f43c24da97bb20f7a438c07178ae0";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			
			data = "";
			String line = "";
			
			RestTemplate connection = new RestTemplate();
			data = connection.getForObject(url, String.class);
			objectJ = (JSONObject) parser.parse(data);
			main = (JSONObject) objectJ.get("main");
			obj.put("name", objectJ.get("name"));
			obj.put("temperature", main.get("temp"));
			obj.put("humidity", main.get("humidity"));
			obj.put("date", dtf.format(now));
			
			weather = new Weather();
			weather.setNomeCitta(objectJ.get("name").toString());
			weather.setData(now);
			weather.setTemperatura(Double.parseDouble(main.get("temp").toString()));
			weather.setUmidita(Double.parseDouble(main.get("humidity").toString()));
			
			String nome = weather.getNomeCitta();
			System.out.println(nome);
			System.out.println("Anno: "+ now.getYear() + "Mese: " + now.getMonthValue() + "Giorno: " + now.getDayOfMonth());
			
			dataSet.addWeather(weather);
			
			return obj;
		}
		catch(IOException e) {
				obj.put("name", "zipCode errato o non disponibile");
				obj.put("temperature", "wrong or unavailable zip code");
				obj.put("humidity", "falsche oder nicht verfügbare Postleitzahl");
				obj.put("date", "code postal incorrect ou indisponible");
				return obj;
		}
		
	}
	
	/*
	 * Getter and Setters WeathersUtils
	 */

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public JSONObject getObjectJ() {
		return objectJ;
	}

	public void setObjectJ(JSONObject objectJ) {
		this.objectJ = objectJ;
	}

	public JSONObject getMain() {
		return main;
	}

	public void setMain(JSONObject main) {
		this.main = main;
	}

	public JSONParser getParser() {
		return parser;
	}

	public void setParser(JSONParser parser) {
		this.parser = parser;
	}

	public JSONObject getWeather() {
		return obj;
	}

	public void setWeather(JSONObject obj) {
		this.obj = obj;
	}
}
