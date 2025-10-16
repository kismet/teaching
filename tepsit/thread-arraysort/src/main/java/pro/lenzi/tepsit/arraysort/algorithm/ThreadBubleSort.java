
package pro.lenzi.tepsit.arraysort.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import pro.lenzi.util.Files;
import pro.lenzi.util.Threads;

public class ThreadBubleSort {

	class Chunck {
		int inizio;
		int fine;
		int current;
	}
	
	private String path;
	private String[] lines =  null;
	private int threads = -1;
	
	public ThreadBubleSort(String p, int t) {
		this.path = p;
		this.threads = t;
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
		int n = this.threads;
		Thread[] threads = new Thread[n];
		
		//dimensione di ogni blocco da assegnare ad un thread
		int blocco = (end-start)/(n-1); 
		for (int i = 0; i < n; i++) {
			int inizio = i*blocco;
			int fine = (i+1)*blocco;
			if(fine > end) {
				fine = end;
			}
			OrderingThread o = new OrderingThread(lines, inizio, fine);
			threads[i] = new Thread(o,"Ordering-"+i+"-["+inizio+","+fine+"]");
			threads[i].start();
			//Avvio i thread
		}
		//Aspetto la fine dei Thread
		Threads.joinAll(threads);
		long begin = System.currentTimeMillis();
		//Effetto il merge tra i vari spezzoni che ora sono ordinati
		smartMerge();
		long delta = System.currentTimeMillis()-begin;
		System.out.printf("Merged data from Threads in %d ms\n",delta);
	}

	private void smartMerge() {
		Chunck[] parts =  new Chunck[threads];
		for (int i = 0; i < threads; i++) {
			parts[i] = new Chunck();
			parts[i].inizio = i*(lines.length/(threads-1));
			parts[i].fine = Math.min( (i+1)*(lines.length/(threads-1)), lines.length );
			parts[i].current = parts[i].inizio;
		}
		ArrayList<Chunck> list = new ArrayList<ThreadBubleSort.Chunck>(Arrays.asList(parts));
		for (Iterator i = list.iterator(); i.hasNext();) {
			Chunck c = (Chunck) i.next();
			if(c.fine == c.current) {
				i.remove();
			}
		}
		String[] ordered = new String[lines.length];
		int filling = 0;
		while(list.isEmpty() == false && filling < ordered.length) {
			int minIdx = 0;
			String minValue = lines[list.get(minIdx).current];
			for (int i = 1; i < list.size(); i++) {
				String current = lines[list.get(i).current];
				if(minValue.compareTo(current)>0) {
					minIdx = i;
					minValue = lines[list.get(minIdx).current];
				}
			}
			ordered[filling] = minValue;
			filling++;
			list.get(minIdx).current++;
			if(list.get(minIdx).current>=list.get(minIdx).fine) {
				list.remove(minIdx);
				System.out.println("Completed list "+minIdx+" remaing "+list.size());
			}
		}
		if( list.size() > 1 ) {
			throw new RuntimeException("Left too many list:"+list.size());
		}else if(list.size() == 1 ) {
			Chunck last = list.get(0);
			while(last.current < last.fine) {
				ordered[filling] = lines[last.current];
				last.current++;
				filling++;
			}
		}
		if(filling!=lines.length) {
			throw new RuntimeException("We haven't copied all the lines:"+(lines.length-filling));
		}
		lines = ordered;
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
	
	public static void main(String[] args) {
		String path = null;
		System.out.println("\n\nRunning "+ThreadBubleSort.class.getName());
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
		ThreadBubleSort app = new ThreadBubleSort(path, 16);
		app.avvia();
	}
}
