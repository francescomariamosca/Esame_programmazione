package it.univpm.EsameProgrammazione.Utilities;
import it.univpm.EsameProgrammazione.Model.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;    
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherUtils {
	protected String data;
	protected JSONObject objectJ;
	protected JSONArray weathers;
	protected JSONObject main;
	protected JSONParser parser;
	protected JSONObject weather;
	protected DataSet dataSetArray;
	
	public WeatherUtils() {
		this.data = "";
		this.objectJ = new JSONObject();
		this.main = new JSONObject();
		this.parser = new JSONParser();
		this.weathers = new JSONArray();
		this.weather = new JSONObject();
		this.dataSetArray = new DataSet();
	}
	
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
		}catch(Exception e) {
			System.out.println("Sarà quel che sarà");
		}
			objectJ = (JSONObject) parser.parse(data);
			main = (JSONObject) objectJ.get("main");
			weather.put("name", objectJ.get("name"));
			weather.put("temperature", main.get("temp"));
			weather.put("humidity", main.get("humidity"));
			weather.put("date", dtf.format(now));
			
			weathers.add(weather);
			dataSetArray.setArrayWeathers(weathers);
		
			return weather;

	}

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

	public JSONArray getWeathers() {
		return weathers;
	}

	public void setWeathers(JSONArray weathers) {
		this.weathers = weathers;
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
		return weather;
	}

	public void setWeather(JSONObject weather) {
		this.weather = weather;
	}

	public DataSet getDataSetArray() {
		return dataSetArray;
	}

	public void setDataSetArray(DataSet dataSetArray) {
		this.dataSetArray = dataSetArray;
	}
}
