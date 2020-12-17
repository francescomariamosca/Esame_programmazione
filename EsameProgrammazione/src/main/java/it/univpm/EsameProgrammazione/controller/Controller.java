package it.univpm.EsameProgrammazione.controller;

import it.univpm.EsameProgrammazione.Dizionario.DizionarioImpl;
import it.univpm.EsameProgrammazione.Model.Weather;
import it.univpm.EsameProgrammazione.Utilities.DataSet;
import it.univpm.EsameProgrammazione.Utilities.DownloadDataSet;
import it.univpm.EsameProgrammazione.Utilities.WeatherUtils;

import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	public String API = "d35f43c24da97bb20f7a438c07178ae0";
	public String url = "";
	public WeatherUtils callWeather = new WeatherUtils();
	protected DataSet dataSet = new DataSet();
	private DownloadDataSet download = new DownloadDataSet();
	
	/*
	 * rotta di tipo GET per cercare informazioni relative alla temperatura e umidità
	 * partendo dallo zipCode
	 * @return ritorna un JSONObject con le informazioni richieste
	 * @param il parametro richiesto è lo zipCode
	 */
	@GetMapping("/Cerca")
	public JSONObject getData(@RequestParam(name = "zipCode", defaultValue = "") String zipCode) throws ClassNotFoundException, IOException, ParseException {
		boolean flag = false;
		if(dataSet.isEmpty()) {
			dataSet.setWeathers(download.DownloadArray());
			flag = true;
		}
		System.out.print(zipCode);
		return callWeather.chiamataAPI(zipCode, dataSet, flag);
	}
	
	@GetMapping("/pippo")
	public String getArray() {
		return dataSet.toString();
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
	 * @param sottostringa tramite quale fare la ricerca
	 * @return
	 */
	@GetMapping("/Ricerca")
	public JSONArray getSottostringa(@RequestParam(name = "q", defaultValue = "")String sottostringa){
		JSONArray cittaSottostringa;
		DizionarioImpl dizionarioSottostringa = new DizionarioImpl();
		cittaSottostringa = dizionarioSottostringa.cercaSottostringa(sottostringa);
		return  cittaSottostringa;
	}
}
