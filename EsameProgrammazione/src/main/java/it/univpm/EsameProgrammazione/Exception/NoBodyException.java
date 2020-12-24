package it.univpm.EsameProgrammazione.Exception;

public class NoBodyException extends Exception{
	public NoBodyException() {
		super("IL BODY DELLA RICHIESTA E' VUOTO");
	}
}
