package it.univpm.EsameProgrammazione.controller;

import it.univpm.EsameProgrammazione.Dizionario.DizionarioImpl;
import it.univpm.EsameProgrammazione.Utilities.WeatherUtils;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	protected JSONArray arrayWeather = new JSONArray();
	protected JSONObject objectWeather = new JSONObject();
	public String API = "d35f43c24da97bb20f7a438c07178ae0";
	public String url = "";
	public WeatherUtils callWeather = new WeatherUtils();
	/*
	 * rotta di tipo GET per cercare informazioni relative alla temperatura e umidità
	 * partendo dallo zipCode
	 * @return ritorna un JSONObject con le informazioni richieste
	 * @param il parametro richiesto è lo zipCode
	 */
	@GetMapping("/Cerca")
	public JSONObject getData(@RequestParam(name = "zipCode", defaultValue = "") String zipCode) throws ParseException {
		System.out.print(zipCode);
		return callWeather.chiamataAPI(zipCode, true);
	}
	
	/*
	 * Prova per vedere se il vettore di array è popolato o meno
	 * la toglierò alla fine
	 */
	@GetMapping("/pippo")
	public JSONArray getData() {
		return callWeather.getWeathers();
	}

	/**
	 * rotta per visualizzare i capoluoghi con i rispettivi zipCode
	 * @return ritorna un JSONArray contenente i capoluoghi
	 */
	@GetMapping("/Suggest")
	public JSONArray getCitta(){
		JSONArray citta = new JSONArray();
		DizionarioImpl dizionario = new DizionarioImpl();
		citta = dizionario.visualizzaDizionario();
		return citta;
	}
	

}
