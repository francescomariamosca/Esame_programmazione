package it.univpm.EsameProgrammazione.Exception;

import org.json.simple.JSONObject;

public class StatsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatsException() {
		super();
	}
	
	public StatsException(String msg) {
		super(msg);
	}
	
	public StatsException(JSONObject object) {
		object.put("errore", "NON CI SONO DATI");
	}

}
