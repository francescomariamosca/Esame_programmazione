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

/**
 * Classe che si occupa di caricare le informazioni
 * del DataSet alla partenza 
 */
public class DownloadDataSet {
	protected  Vector<Weather> array = new Vector<Weather>();
	
	@SuppressWarnings("unchecked")
	public  Vector<Weather> DownloadArray()  {
		File file = new File("dataSet.txt");
		
	    try {
	    	
	    	if(file.exists() & file.canRead()) {
	    		ObjectInputStream in =
		    			new ObjectInputStream ( new BufferedInputStream (
		    			new FileInputStream (file)));
		    			array = (Vector < Weather >) in.readObject();
		    			in.close ();
		    			System.out.println("Fine download");
	    	}
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
	   
	    return this.array;
	}
}
