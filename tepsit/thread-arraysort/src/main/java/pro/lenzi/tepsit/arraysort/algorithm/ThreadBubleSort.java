
package pro.lenzi.tepsit.arraysort.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import pro.lenzi.util.Files;

public class ThreadBubleSort {

	private String path;
	private String[] lines =  null;
	
	public ThreadBubleSort(String p) {
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
		ordina(lines,0,lines.length);
		long after = System.currentTimeMillis();
		System.out.printf(
				"Ordinate %6d linee in: %5d ms\n", 
				lines.length, (after-before)
		);		
	}
	
	//Dividere il compito di ordinare in blocchi
	private void ordina(String[] vet, int start, int end) {
		
		//Qui andrà il numero di thread da creare
		int n = -1; 
		
		//dimensione di ogni blocco da assegnare ad un thread
		int blocco = (end-start)/n; 
		for (int i = 0; i < n; i++) {
			int inizio = i*blocco;
			int fine = (i+1)*blocco;
			if(fine > end) {
				fine = end;
			}
			//Ordinatore è il nome della classe che voglio usare per ordinare
			//Ordinatore o = new Ordinatore(vet,inizio,fine);
			
			//Avvio i thread
		}
		
		//Aspetto la fine dei Thread
		
		//Effetto il merge tra i vari spezzoni che ora sono ordinati
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
	}
	
	
	public static void main(String[] args) {
		String path = null;
		if(args.length>0) {
			System.out.printf(
					"Uso %s come nome del file da elaborare",args[0]
			);
			path = args[0];
		} else {
			Scanner s = new Scanner(new BufferedReader(
					new InputStreamReader(System.in)
			));
			
			System.out.print("Inserisci il file da leggere\n -> ");
			path = s.next();
		}
		ThreadBubleSort app = new ThreadBubleSort(path);
		app.avvia();
	}
}
