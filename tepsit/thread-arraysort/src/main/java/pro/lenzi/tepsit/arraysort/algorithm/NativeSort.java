package pro.lenzi.tepsit.arraysort.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import pro.lenzi.util.Files;

public class NativeSort {

	private String path;
	private String[] lines =  null;
	
	public NativeSort(String p) {
		this.path = p;
	}

	private void leggi() {
		long before = System.currentTimeMillis();
		lines = Files.readWholeFile(path);
		long after = System.currentTimeMillis();
		System.out.printf(
				"Letto %6d linee del file %s in:\n%5d ms\n", 
				lines.length, path, (after-before)
		);
	}
	
	private void ordina() {
		long before = System.currentTimeMillis();
		Arrays.sort(lines);
		long after = System.currentTimeMillis();
		System.out.printf(
				"Ordinate %6d linee in: %5d ms\n", 
				lines.length, (after-before)
		);		
	}
	
	private void stampa(int n) {
		int end = lines.length;
		if(n > 0) {
			end = n;
		}else if(n < 0) {
			end = end + n;
		}
		for (int i = 0; i < end; i++) {
			System.out.println(lines[i]);
		}
	}
	
	public void avvia() {
		leggi();
		ordina();
		//stampa(10);
		scriviRisultati();
	}
	
	private void scriviRisultati() {
		File f = new File(path);
		File d = f.getParentFile();
		File dst = new File(d,this.getClass().getName()+"-"+f.getName());
		Files.createTextFile(dst.getAbsolutePath(),lines);		
	}
	
	public String[] getLines() {
		avvia();
		return lines;
	}
	
	public static void main(String[] args) {
		String path = null;
		System.out.println("\n\nRunning "+NativeSort.class.getName());
		if(args.length>0) {
			System.out.printf(
					"Uso %s come nome del file da elaborare\n",args[0]
			);
			path = args[0];
		} else {
			Scanner s = new Scanner(new BufferedReader(
					new InputStreamReader(System.in)
			));
			
			System.out.print("Inserisci il file da leggere\n -> ");
			path = s.next();
		}
		NativeSort app = new NativeSort(path);
		app.avvia();
	}
}
