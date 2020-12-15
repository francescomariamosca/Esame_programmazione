package it.univpm.EsameProgrammazione.Utilities;

import it.univpm.EsameProgrammazione.Model.Weather;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
 * Classe che realizza le chiamate API
 * il parsing in JSON e popola il vettore di arrayJSON
 * che compone il DataSet
 */

public class WeatherUtils implements Serializable {
	protected String data;
	protected JSONObject objectJ;
	//protected JSONArray weathers;
	protected JSONObject main;
	protected JSONParser parser;
	protected JSONObject obj;
	
	protected DataSet dataSetArray;
	protected Weather weather;
	
	public WeatherUtils() {
		this.data = "";
		this.objectJ = new JSONObject();
		this.main = new JSONObject();
		this.parser = new JSONParser();
		this.obj = new JSONObject();
		this.dataSetArray = new DataSet();
		this.weather = new Weather(0, 0, 0, "");
	}
	
	/*
	 * Funzione che realizza la chiamata API, effettua il parsing
	 * e popola il vettore di array JSON
	 */
	
	public JSONObject chiamataAPI(String zipCode, boolean isObject) throws ParseException {
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
			/*try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
			   }
			} finally {
				in.close();
			}*/
			objectJ = (JSONObject) parser.parse(data);
			main = (JSONObject) objectJ.get("main");
			obj.put("name", objectJ.get("name"));
			obj.put("temperature", main.get("temp"));
			obj.put("humidity", main.get("humidity"));
			obj.put("date", dtf.format(now));
			
			weather.setNomeCitta(objectJ.get("name").toString());
			weather.setData(dtf.format(now));
			weather.setTemperatura(Double.parseDouble(main.get("temp").toString()));
			weather.setUmidita(Double.parseDouble(main.get("humidity").toString()));
			dataSetArray.addWeather(weather);


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

	public DataSet getDataSetArray() {
		return dataSetArray;
	}

	public void setDataSetArray(DataSet dataSetArray) {
		this.dataSetArray = dataSetArray;
	}
}
