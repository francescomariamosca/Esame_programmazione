package Controller;

import Utilities.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
	protected String API = "d35f43c24da97bb20f7a438c07178ae0";
	private String url;
	private JSONObject risposta;
	private WeatherParser parser;

	/*
	 * Cerca per zipCode farà
	 * 1 Prende due parametri country e zip
	 * 2 Lancia la funzione che fa la chiamata e popola i vettori che restituisce
	 * 3 Da la risposta
	 */
	
	// Passa al Parser per la chiamata e il parse l'url con l'api e i parametri dentro
	// api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={API key}
	
	@GetMapping("/cerca")
	public JSONObject getData(@RequestParam(name = "zipCode", defaultValue = "") int zipCode) {
		url = "http://api.openweathermap.org/data/2.5/weather?zip="+zipCode+",it&appid=d35f43c24da97bb20f7a438c07178ae0";
		//risposta = parser.getData(url);
		return risposta;
	}
}
