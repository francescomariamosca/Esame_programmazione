package Utilities;
import Model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherParser {
	private JSONArray arrayJ;
	private JSONObject main;
	private String inline;
	protected Weather weather;
	protected Vector<Weather> weathers;
	
	/*
	 * Funzione che fa il parse
	 * popola il vettore e lo restituisce
	 */
	
	/*public JSONObject getData(String url) {
		try{
			URL url_chiamata = new URL(url);
			url_chiamata.openConnection();
			
			Scanner scanner = new Scanner(url_chiamata.openStream());
			
			while(scanner.hasNext()) {
				inline += scanner.nextLine();
			}
			System.out.println(inline);
			scanner.close();

			// Creo il parser
			JSONParser parse = new JSONParser();
			JSONObject obj = (JSONObject) parse.parse(inline);
			JSONObject main = (JSONObject) obj.get("main");
			JSONObject base = (JSONObject) obj.get("base");
			weather.setTemperatura(Double.parseDouble(main.get("temp").toString()));
			weather.setUmidita(Double.parseDouble(main.get("humidity").toString()));
			weather.setNomeCitta(base.get("name").toString());
			
			weathers.add(weather);
			System.out.println(weather);
		
		}
		catch(Exception e) {
			System.out.println("PARSER PROBLEM");
		}
		
		return main;
		
	}*/

}
