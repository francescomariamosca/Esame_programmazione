package it.univpm.EsameProgrammazione.Exception;

public class NoDataException extends Exception{
	
	public NoDataException() {
		super("NON CI SONO DATI NEL DATASET");
	}
}
