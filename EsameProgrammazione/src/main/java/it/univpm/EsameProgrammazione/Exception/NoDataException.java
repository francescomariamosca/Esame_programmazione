package it.univpm.EsameProgrammazione.Exception;

/**
 * Eccezione riguardante l'assenza di dati nel dataset.
 */
public class NoDataException extends Exception{
	
	public NoDataException() {
		super("NON CI SONO DATI NEL DATASET");
	}
}
