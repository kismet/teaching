package pro.lenzi.tepsit.arraysort.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import pro.lenzi.util.Files;

public class SingleBubleSort {

	private String path;
	private String[] lines =  null;
	
	public SingleBubleSort(String p) {
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
	
	private void ordina(String[] vet, int start, int end) {
		for (int j = start; j < end; j++) {
			for (int i = j+1; i < end; i++) {
				if(vet[j].compareTo(vet[i])>0) {
					String aux = vet[i];
					vet[i] = vet[j];
					vet[j] = aux;
				}
			}
		}
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
		System.out.println("\n\nRunning "+SingleBubleSort.class.getName());
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
		SingleBubleSort app = new SingleBubleSort(path);
		app.avvia();
	}
}
