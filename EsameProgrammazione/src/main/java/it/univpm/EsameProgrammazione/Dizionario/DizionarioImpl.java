package it.univpm.EsameProgrammazione.Dizionario;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 *
 * Classe che si occupa dell' implementazione dei metodi  dell' interfaccia dizionario
 *
 */
public class DizionarioImpl implements InterfacciaDizionario{


    Vector<Dizionario> dizionario = new Vector<>();  // vettore che contiene le città prese dal file
    JSONArray dizionarioCompleto = new JSONArray(); // JSONArray delle città esempio

    /**
     * implementazione tramite override del metodo popolaDizionario,
     * il quale popola il dizionario dal file capoluoghi.txt
     */
    @Override
    public void popolaDizionario() {


        try{
            Scanner file_input = new Scanner(new BufferedReader(new FileReader("capoluoghi.txt")));
            while(file_input.hasNext()) {

                String riga = file_input.nextLine();
                String[] parti = riga.split(":"); // divide ogni riga in due parti separate dai ':'
                Dizionario citta = new Dizionario();
                citta.nomeCitta = parti[0];
                citta.zipCode = parti[1];
                dizionario.add(citta);

            }
            file_input.close();

        } catch (IOException e) {
            System.out.println("ERRORE DI I/O");
            e.printStackTrace();

        }


    }

    /**
     * Funzione per visualizzare la lista delle città che possono essere inserite
     * @return ritorna il JSONArray con tutti i capoluoghi con i rispettivi zipCode e nome della città.
     */
    @Override
    public JSONArray visualizzaDizionario() {


        popolaDizionario();
        for(int i = 0; i<dizionario.capacity(); i++){
            JSONObject objCitta = new JSONObject();
            objCitta.put("zipCode", dizionario.elementAt(i).zipCode);
            objCitta.put("name", dizionario.elementAt(i).nomeCitta);
            dizionarioCompleto.add(objCitta); // inserimento di ciascun JSONObject nel JSONArray
        }
        return dizionarioCompleto;
    }

    /**
     * metodo che permette di cercare una città inserendo una sottostringa
     * @param sottostringa la sottostringa che l'utente inserisce per cercare la città
     * @return ritorna un JSONArray di tutte le città che contengono la sottostringa inserita.
     */
    @Override
    public JSONArray cercaSottostringa (String sottostringa){

        // creo un JSONArray in cui inserirò tuti i JSONObject che contengono la sottostringa nel loro attributo nome
        JSONArray stringaCitta = new JSONArray();
        popolaDizionario();
        for (int i = 0; i < dizionario.size(); i++) {
            JSONObject objCitta = new JSONObject();
            objCitta.put("name", dizionario.elementAt(i).nomeCitta);
            objCitta.put("Zip Code", dizionario.elementAt(i).zipCode);
            if (dizionario.elementAt(i).nomeCitta.contains(sottostringa)) {  // verifico se contiene la sottostringa
                stringaCitta.add(objCitta); // inserisco solo i JSONObject che contengono la sottostringa
            }

        }
        return stringaCitta;
    }
}
