package it.univpm.EsameProgrammazione.controller;

import it.univpm.EsameProgrammazione.Dizionario.DizionarioImpl;
import it.univpm.EsameProgrammazione.Utilities.WeatherUtils;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
	
	public String API = "d35f43c24da97bb20f7a438c07178ae0";
	public String url = "";
	public WeatherUtils callWeather = new WeatherUtils("");
	
	@GetMapping("/Cerca")
	public String getData(@RequestParam(name = "zipCode", defaultValue = "") String zipCode) {
		url = "http://api.openweathermap.org/data/2.5/weather?zip="+zipCode+",it&appid="+this.API+"";
		return callWeather.chiamataAPI(url, true);
	}

	/**
	 * rotta per visualizzare i capoluoghi con i rispettivi zipCode
	 * @return ritorna un JSONArray contenente i capoluoghi
	 */
	@GetMapping("/Suggest")
	public JSONArray getCitta(){
		JSONArray citta;
		DizionarioImpl dizionario = new DizionarioImpl();
		citta = dizionario.visualizzaDizionario();
		return citta;
	}



	/**
	 * rotta per visualizzare i capoluoghi con i rispettivi zipCode, che contengono la sottostringa immessa
	 * @return ritorna un JSONArray contenente i capoluoghi che contengono la stirga immessa
	 */
	@GetMapping("/Sottostringa")
	public JSONArray getSottostringa(@RequestParam(name = "q", defaultValue = "")String sottostringa){
		JSONArray cittaSottostringa;
		DizionarioImpl dizionarioSottostringa = new DizionarioImpl();
		cittaSottostringa = dizionarioSottostringa.cercaSottostringa(sottostringa);
		return  cittaSottostringa;
	}

}
