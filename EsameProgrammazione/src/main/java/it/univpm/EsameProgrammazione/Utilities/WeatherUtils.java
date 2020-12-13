package it.univpm.EsameProgrammazione.Utilities;
import it.univpm.EsameProgrammazione.Model.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherUtils {
	protected String data;
	protected JSONObject objectJ;
	protected JSONArray arrayJ;
	protected JSONParser parser;
	protected Weather weather;
	
	public WeatherUtils(String data) {
		this.data = data;
		this.objectJ = new JSONObject();
		this.arrayJ = new JSONArray();
		this.parser = new JSONParser();
	}
	
	public String chiamataAPI(String url, boolean isObject) {
		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			
			data = "";
			String line = "";
			try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
			   }
			} finally {
			   in.close();
			}
		}catch(Exception e) {
			System.out.println("Sarà quel che sarà");
		}
		return data;
	}
}
