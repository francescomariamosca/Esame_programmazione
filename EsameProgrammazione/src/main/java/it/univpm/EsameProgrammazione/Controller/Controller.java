package it.univpm.EsameProgrammazione.Controller;

import it.univpm.EsameProgrammazione.Dizionario.DizionarioImpl;
import it.univpm.EsameProgrammazione.Exception.NoBodyException;
import it.univpm.EsameProgrammazione.Exception.NoDataException;
import it.univpm.EsameProgrammazione.Filter.Filters;
import it.univpm.EsameProgrammazione.Utilities.DataSet;
import it.univpm.EsameProgrammazione.Utilities.DownloadDataSet;
import it.univpm.EsameProgrammazione.Utilities.WeatherUtils;

import java.util.*;

import it.univpm.EsameProgrammazione.Stats.Stats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 * Classe controller che gestisce tutte le chiamate
 */
@RestController
public class Controller {

    public WeatherUtils callWeather = new WeatherUtils();
    public DataSet dataSet = new DataSet();
    Timer t = new Timer();

    private DownloadDataSet download = new DownloadDataSet();


    /**
     * rotta di tipo GET per cercare informazioni relative alla temperatura e umidità
     * partendo dallo zipCode
     *
     * @param zipCode il parametro richiesto è lo zipCode
     * @return ritorna un JSONObject con le informazioni richieste
     */
    @GetMapping("/Cerca")
    public JSONObject getData(@RequestParam(name = "zipCode", defaultValue = "") String zipCode) throws ParseException {
        if (dataSet.isEmpty()) {
            dataSet.setWeathers(download.DownloadArray());
            // parte il timer per il salvataggio dei dati ogni ora
            t.scheduleAtFixedRate(dataSet, 0, 3600 * 1000);
        }
        return callWeather.chiamataAPI(zipCode, dataSet);
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
     * @return JSONArray con tutte le città che contengono la sottostringa
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
     *
     * @param nomecitta nome della citta cercata
     * @return JSONArray delle statistiche
     * @throws NoDataException Eccezione che viene lanciata quando non sono presenti dati nel dataSet
     */
    @GetMapping("/Stats")
    public JSONArray getStatistiche(@RequestParam(name = "nomeCitta", defaultValue = "") String nomecitta) throws NoDataException {
        if (dataSet.isEmpty()) {
            dataSet.setWeathers(download.DownloadArray());
            // parte il timer per il salvataggio dei dati ogni ora
            t.scheduleAtFixedRate(dataSet, 0, 3600 * 1000);
        }
        Stats stats = new Stats();
        JSONArray statistiche = new JSONArray();
        stats.caricaArrayTemperatura(dataSet, nomecitta);
        stats.caricaArrayUmidita(dataSet, nomecitta);
        statistiche.add(stats.statsTemperatura());
        statistiche.add(stats.statsUmidita());
        return statistiche;
    }

    /**
     * Messaggio NoDataException
     * @param e Eccezione
     * @return Messaggio di errore
     */
    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<Object> handleIOException(NoDataException e) {
        return new ResponseEntity<>(DomainError(), HttpStatus.BAD_REQUEST);
    }

    public <Object> String DomainError() {
        return "Non ci sono dati nel dataSet, riprovare con il nome di un'altra città.";
    }

    @ExceptionHandler(NoBodyException.class)
    public ResponseEntity<Object> handleIOException(NoBodyException e) {
        return new ResponseEntity<>(BodyError(), HttpStatus.BAD_REQUEST);
    }

    public <Object> String BodyError() {
        return "Il body della richiesta è vuoto. Riempilo correttamente e riprova.";
    }

    /**
     * rotta per visualizzare le statistiche tramite dei range(giornaliero, settimanale, personalizzabile)
     *
     * @param infoFilter body contenente il nome e il tipo del range
     * @return JSONArray delle statistiche
     * @throws NoBodyException Eccezione che viene lanciata quando il body per la richiesta è vuoto
     */
    @PostMapping("/Filters")
    public JSONArray getFilters(@RequestBody JSONObject infoFilter) throws NoBodyException {
        if (dataSet.isEmpty()) {
            dataSet.setWeathers(download.DownloadArray());
            // parte il timer per il salvataggio dei dati ogni ora
            t.scheduleAtFixedRate(dataSet, 0, 3600 * 1000);
        }
        if (infoFilter.isEmpty()) throw new NoBodyException();
        Filters filtri = new Filters();
        JSONArray filters = new JSONArray();

        String nomeCitta = (String) infoFilter.get("name");
        int tipo = (int) infoFilter.get("range");

        filtri.tipoFiltro(dataSet, tipo, nomeCitta);
        filters.add(filtri.statsTemperatura());
        filters.add(filtri.statsUmidita());
        return filters;
    }

}
