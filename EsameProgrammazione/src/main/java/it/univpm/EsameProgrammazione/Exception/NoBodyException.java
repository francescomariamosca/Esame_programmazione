package it.univpm.EsameProgrammazione.Exception;

/**
 * Eccezione riguardante l'assenza del body.
 */
public class NoBodyException extends Exception{
	public NoBodyException() {
		super("IL BODY DELLA RICHIESTA E' VUOTO");
	}
}
