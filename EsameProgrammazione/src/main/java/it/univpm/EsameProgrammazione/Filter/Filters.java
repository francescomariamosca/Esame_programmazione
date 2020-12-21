package it.univpm.EsameProgrammazione.Filter;

import it.univpm.EsameProgrammazione.Utilities.DataSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import it.univpm.EsameProgrammazione.stats.statstemperature;
public class Filters {


    Vector<Double> temperatura = new Vector <>();
    Vector <Double> umidita = new Vector <>();
    double averageTemperature=0;
    double averageHumidity=0;
    double varianceTemperature=0;
    double varianceHumidity=0;

    LocalDateTime now = LocalDateTime.now();

    // passo a questa funzione il filtro inserito dall'utente(giornaliero, sett)
    // il rang personalazzibaile mi da direttamente un valore numerico
    // e lo converto in un valore numerico
    public void tipoFiltro(DataSet dataSet, int tipo, String nomeCitta) {
        String tempo;

        if (tipo == 1) {
            int giorno = now.getDayOfYear();
            giornaliero(dataSet,nomeCitta,giorno);
        }
    }

        // la funzione per il settimanle sarà la stesse del range personalizzabile, bast solo impostare
        // il range a 7 giorni
        public void giornaliero(DataSet dataSet, String nomeCitta, int giorno){
            for (int i = 0; i < dataSet.getWeathers().size(); i++) {
                if (dataSet.getWeathers().get(i).getData().getDayOfYear() == giorno && dataSet.getWeathers().get(i).getNomeCitta().equals(nomeCitta)){
                    temperatura.add(dataSet.getWeathers().get(i).getTemperatura());
                    umidita.add(dataSet.getWeathers().get(i).getUmidita());
                }
            }
        }
    public JSONObject statsTemperatura(String a) {//qual è il problema, il problema è...
        JSONObject object = new JSONObject();
        try {
            Object maxT = Collections.max(temperatura);
            object.put("Temperatura massima:", maxT);
            Object minT = Collections.min(temperatura);
            object.put("Temperatura minima:", minT);

            //average
            for (int i=0; i<temperatura.size(); i++) {
                averageTemperature += temperatura.get(i);
            }
            averageTemperature /= temperatura.size();
            object.put("La media:", averageTemperature);

            //variance
            for (int i=0; i<temperatura.size(); i++) {
                varianceTemperature += Math.pow(temperatura.get(i)-averageTemperature ,2);
            }

            object.put("La varianza:", varianceTemperature);

        }
        catch (Exception e) {
            System.out.println("Nome non presente, non è possibile ottenere la temperatura");
        }
        return object;
    }

    public JSONObject statsUmidita (String a) {
        JSONObject object = new JSONObject();
        try {
            Object maxU = Collections.max(umidita);
            object.put("Umidità massima:", maxU);
            Object minU = Collections.min(umidita);
            object.put("Umidità minima:", minU);

            //average
            for (int i=0; i<umidita.size(); i++) {
                averageHumidity += umidita.get(i);
            }
            averageHumidity /= umidita.size();
            object.put("La media per l'umidita:", averageHumidity);

            //variance
            for (int i=0; i<umidita.size(); i++) {
                varianceHumidity += Math.pow(umidita.get(i)-averageHumidity ,2);
            }

            object.put("La varianza per l'umidita:", varianceHumidity);


        } catch (Exception e) {
            System.out.println("Nome non presente, riprovare");
        }

        return object;
    }


    }
