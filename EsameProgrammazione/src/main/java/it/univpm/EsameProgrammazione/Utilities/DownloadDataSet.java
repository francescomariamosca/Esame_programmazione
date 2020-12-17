package it.univpm.EsameProgrammazione.Utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import it.univpm.EsameProgrammazione.Model.Weather;

public class DownloadDataSet {
	protected  Vector<Weather> array = new Vector<Weather>();
	private  File file;
	public  Vector<Weather> DownloadArray() {
		file = new File("dataSet.txt");
	    try {
	    	if(file.exists()) {
	    	ObjectInputStream in =
	    			new ObjectInputStream ( new BufferedInputStream (
	    			new FileInputStream (file)));
	    			array = ( Vector < Weather >) in.readObject ();
	    			in.close ();
	    	}
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
	    return array;
	}
}
