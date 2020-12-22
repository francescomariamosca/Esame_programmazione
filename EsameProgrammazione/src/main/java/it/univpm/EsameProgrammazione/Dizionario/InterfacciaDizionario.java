package it.univpm.EsameProgrammazione.Dizionario;

import org.json.simple.JSONArray;

/**
 * @author Francesco Maria Mosca
 * @author Dennis Pierantozzi
 * @author Nicola Mochi
 *
 * interfaccia del dizionario
 */
public interface InterfacciaDizionario {

    /**
     * popola il vettore dizionario
     */
     void popolaDizionario();

    /**
     * @return ritona un jsonArray contenente tutti i capoluoghi con i rispettivi zipCode.
     */
    JSONArray visualizzaDizionario();

    /**
     *
     * @param sottostringa, la sottostringa che l'utente inserisce per cercare la città
     * @return ritorna un JsonArray di tutte le citta del dizionario che comprendono la sottostringa
     */
     JSONArray cercaSottostringa(String sottostringa);

}
