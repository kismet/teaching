package pro.lenzi.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Files {

	/**
	 * Questa funzione ha il solo scopo didattico di semplificare
	 * la lettura di un file in Java, ma è sconsigliata perchè
	 * mette in memoria Tutto il File e non ottimizza neanche la
	 * creazione degli oggetti
	 * @param path il percorso del file da leggere (assoluto o relativo)
	 * @return un array di {@link String} delle righe lette
	 */
	public static String[] readWholeFile(String path) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new String[] {};
		}
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		try {
			while ( (line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String[]) lines.toArray(new String[] {});
	}
	
	
	public static boolean createTextFile(String path,String[] lines) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			return false;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedOutputStream(fos));
		for (int i = 0; i < lines.length; i++) {
			pw.println(lines[i]);
		}
		pw.flush();
		pw.close();
		return true;
	}
	
}
