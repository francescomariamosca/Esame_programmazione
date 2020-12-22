package it.univpm.EsameProgrammazione.Filter;

import it.univpm.EsameProgrammazione.Utilities.DataSet;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Vector;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 * classe che si occupa della gstione dei filtri
 */

public class Filters {


    Vector<Double> temperatura = new Vector <>();
    Vector <Double> umidita = new Vector <>();
    double averageTemperature=0;
    double averageHumidity=0;
    double varianceTemperature=0;
    double varianceHumidity=0;

    LocalDateTime now = LocalDateTime.now();

    // passo a questa funzione il filtro inserito dall'utente(giornaliero, sett) in base sl numero
    public void tipoFiltro(DataSet dataSet, int tipo, String nomeCitta) {

        if (tipo == 1) {
            int giorno = now.getDayOfYear();
            giornaliero(dataSet,nomeCitta,giorno);
        } else if(tipo == 7){
            int giorno = now.getDayOfYear();
            settimanale(dataSet,nomeCitta,giorno);
        } else {
            int giorno = now.getDayOfYear();
            personalizzato(dataSet,nomeCitta,giorno,tipo);
        }
    }

        public void giornaliero(DataSet dataSet, String nomeCitta, int giorno){
            for (int i = 0; i < dataSet.getWeathers().size(); i++) {
                if (dataSet.getWeathers().get(i).getData().getDayOfYear() == giorno && dataSet.getWeathers().get(i).getNomeCitta().equals(nomeCitta)){
                    temperatura.add(dataSet.getWeathers().get(i).getTemperatura());
                    umidita.add(dataSet.getWeathers().get(i).getUmidita());
                }
            }
        }

        public void settimanale(DataSet dataSet, String nomeCitta, int giorno){
            for (int i = 0; i < dataSet.getWeathers().size(); i++){
                if (dataSet.getWeathers().get(i).getData().getDayOfYear() > (giorno-7) && dataSet.getWeathers().get(i).getNomeCitta().equals(nomeCitta)){
                    temperatura.add(dataSet.getWeathers().get(i).getTemperatura());
                    umidita.add(dataSet.getWeathers().get(i).getUmidita());
                }
            }
        }

        public void personalizzato(DataSet dataSet, String nomeCitta, int giorno, int range){
            for (int i = 0; i < dataSet.getWeathers().size(); i++){
                if (dataSet.getWeathers().get(i).getData().getDayOfYear() > (giorno-range) && dataSet.getWeathers().get(i).getNomeCitta().equals(nomeCitta)){
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
