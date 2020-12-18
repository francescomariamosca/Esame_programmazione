package it.univpm.EsameProgrammazione.Utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.util.Vector;

import it.univpm.EsameProgrammazione.Model.Weather;

public class DownloadDataSet {
	protected  Vector<Weather> array = new Vector<Weather>();
	private  File file;
	@SuppressWarnings("unchecked")
	public  Vector<Weather> DownloadArray() {
		file = new File("dataSet.txt");
		Vector<Weather> provetta = null;
	    try {
	    	
	    	System.out.println("Il file non esiste");
	    	System.out.println("Entrato nell'if prima del download");
	    	ObjectInputStream in =
	    			new ObjectInputStream ( new BufferedInputStream (
	    			new FileInputStream (file)));
	    			provetta = (Vector < Weather >) in.readObject ();
	    			String prova = array.toString();
	    			System.out.println(prova);
	    			in.close ();
	    			System.out.println("Fine download");
	    }
	    catch(OptionalDataException e) {
	    	e.printStackTrace();
	    }
	    catch(FileNotFoundException e) {
	    	e.printStackTrace();
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	    catch(ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	   
	    array = provetta;
	    return array;
	}
}
