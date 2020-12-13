package it.univpm.EsameProgrammazione.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.EsameProgrammazione.Utilities.*;

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
}
