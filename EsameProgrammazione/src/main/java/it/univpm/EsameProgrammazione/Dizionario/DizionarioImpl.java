package it.univpm.EsameProgrammazione.Dizionario;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Classe che si occupa dell' implementazione dei metodi astratti della classe dizionario
 *
 */
public class DizionarioImpl implements InterfacciaDizionario{


    Vector<Dizionario> dizionario = new Vector<>();  // vettore che contiene le città prese dal file
    JSONArray dizionarioCompleto = new JSONArray(); // JSONArray delle 5 città esempio

    /**
     * implementazione tramite override del metodo popolaDizionario
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

    @Override
    public JSONArray cercaSottostringa(String sottostringa) {

        // creo un JSONArray in cui inserirò tuti i JSONObject che contengono la sottostringa nel loro attributo nome
        JSONArray stringaCitta = new JSONArray();
        for(int i = 0; i<dizionario.capacity(); i++){
            JSONObject objCitta = new JSONObject();
            objCitta.put("name", dizionario.elementAt(i).nomeCitta);
            objCitta.put("Zip Code", dizionario.elementAt(i).zipCode);
            if(objCitta.containsKey(sottostringa)){
                stringaCitta.add(objCitta); // inserisco solo i JSONObject che contengono la sottostringa
            }

        }
        return stringaCitta;
    }
}
