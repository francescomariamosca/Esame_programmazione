package it.univpm.EsameProgrammazione.controller;

import it.univpm.EsameProgrammazione.Dizionario.DizionarioImpl;
import it.univpm.EsameProgrammazione.Exception.WeatherException;
import it.univpm.EsameProgrammazione.Filter.Filters;
import it.univpm.EsameProgrammazione.Model.Weather;
import it.univpm.EsameProgrammazione.Utilities.DataSet;
import it.univpm.EsameProgrammazione.Utilities.DownloadDataSet;
import it.univpm.EsameProgrammazione.Utilities.WeatherUtils;

import java.io.IOException;
import java.util.*;

import it.univpm.EsameProgrammazione.stats.statstemperature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

/**
 * classe controller che gestisce tutte le chiamate
 */
@RestController
public class Controller {

	public WeatherUtils callWeather = new WeatherUtils();
	public DataSet dataSet = new DataSet();
	Timer t = new Timer();
	
	protected Vector<Weather> definitiva = new Vector<Weather>();
	private DownloadDataSet download = new DownloadDataSet();


	/**
	 *
	 * rotta di tipo GET per cercare informazioni relative alla temperatura e umidità
	 * partendo dallo zipCode
	 * @return ritorna un JSONObject con le informazioni richieste
	 * @param zipCode il parametro richiesto è lo zipCode
	 */
	@GetMapping("/Cerca")
	public JSONObject getData(@RequestParam(name = "zipCode", defaultValue = "") String zipCode) throws WeatherException, ParseException {
		if (dataSet.isEmpty()) {
			dataSet.setWeathers(download.DownloadArray());
			// parte il timer per il salvataggio dei dati ogni ora
			t.scheduleAtFixedRate(dataSet, 0, 30 * 1000);
		}
		return callWeather.chiamataAPI(zipCode, dataSet);
	}

	@GetMapping("/vedi")
	public void getArray() {
		for (int i = 0; i < dataSet.getWeathers().size(); i++) {
			System.out.println(dataSet.getWeathers().get(i).getNomeCitta());
		}
	}

	/**
	 * rotta per visualizzare i capoluoghi con i rispettivi zipCode
	 *
	 * @return ritorna un JSONArray contenente i capoluoghi
	 */
	@GetMapping("/Suggest")
	public JSONArray getCitta() {
		JSONArray citta;
		DizionarioImpl dizionario = new DizionarioImpl();
		citta = dizionario.visualizzaDizionario();
		return citta;
	}


	/**
	 * rotta per visualizzare i capoluoghi con i rispettivi zipCode, che contengono la sottostringa immessa
	 *
	 * @param sottostringa tramite quale fare la ricerca
	 * @return
	 */
	@GetMapping("/Ricerca")
	public JSONArray getSottostringa(@RequestParam(name = "q", defaultValue = "") String sottostringa) {
		JSONArray cittaSottostringa;
		DizionarioImpl dizionarioSottostringa = new DizionarioImpl();
		cittaSottostringa = dizionarioSottostringa.cercaSottostringa(sottostringa);
		return cittaSottostringa;
	}

	/**
	 * rotta per visualizzare le statistiche.
	 * @param nomecitta nome della citta cercata
	 * @return JSONArray delle statistiche
	 */
	@GetMapping("/Stats")
	public JSONArray getStatistiche(@RequestParam(name = "nomeCitta", defaultValue = "") String nomecitta) throws ClassNotFoundException, IOException, ParseException{
		if (dataSet.isEmpty()) {
			dataSet.setWeathers(download.DownloadArray());
			// parte il timer per il salvataggio dei dati ogni ora
			t.scheduleAtFixedRate(dataSet, 0, 30 * 1000);
		}
		statstemperature stats = new statstemperature();
		JSONArray statistiche = new JSONArray();
		stats.caricaArrayTemperatura(dataSet, nomecitta);
		stats.caricaArrayUmidita(dataSet, nomecitta);
		statistiche.add(stats.statsTemperatura(nomecitta));
		statistiche.add(stats.statsUmidita(nomecitta));
		return statistiche;
	}

	/**
	 * rotta per visualizzare le statistiche tramite dei range(giornaliero, settimanale, personalizzabile)
	 * @param infoFilter body contenente il nome e il tipo del range
	 * @return JSONArray delle statistiche
	 */
	@PostMapping("/Filters")
	public JSONArray getFilters(@RequestBody JSONObject infoFilter) {
		if (dataSet.isEmpty()) {
			dataSet.setWeathers(download.DownloadArray());
			// parte il timer per il salvataggio dei dati ogni ora
			t.scheduleAtFixedRate(dataSet, 0, 30 * 1000);
		}
		Filters filtri = new Filters();
		JSONArray filters = new JSONArray();
		String nomeCitta = (String) infoFilter.get("name");
		int tipo = (int) infoFilter.get("range");
		filtri.tipoFiltro(dataSet, tipo, nomeCitta);
		filters.add(filtri.statsTemperatura(nomeCitta));
		filters.add(filtri.statsUmidita(nomeCitta));
		return filters;
	}

}
