package pro.lenzi.tepsit.thread.multiple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.xml.stream.events.StartDocument;

public class MultiThreadApp {
	
	private static int inizio;
	private static int fine;
	private static int step;
	private static int nThreads;

	
	public void startThemAll() {
		for (int j = 0; j < nThreads; j++) {
			FriendlyCounter c = new FriendlyCounter(inizio,fine,step);
			c.beNice();
			Thread thread = new Thread(c,"Contatore-"+j);
			thread.start();
		}	
	}
	
	public void whoIsCounting() {
		FriendlyCounter unico = new FriendlyCounter(inizio,fine,step);
		for (int j = 0; j < nThreads; j++) {
			Thread thread = new Thread(unico,"Contatore-"+j);
			thread.start();
		}		
	}	
	
	public void noZombieAllowed() {
		//Tutto il codice che segue viene eseguito dal Thread del Main
		Thread[] threads = new Thread[nThreads];
		for (int j = 0; j < threads.length; j++) {
			FriendlyCounter c = new FriendlyCounter(inizio,fine,step);
			threads[j] = new Thread(c,"Contatore-"+j);
		}
		for (int j = 0; j < threads.length; j++) {
			//Avvio i Thread che eseguiranno il codice run della classe
			//FriendlyCounter (vedi riga #38)
			threads[j].start();
		}
		for (int j = 0; j < threads.length; j++) {
			try {
				if(threads[j].isAlive()) {
					threads[j].join();
				}
			} catch (InterruptedException e) {
				//ignoring exception but restaring to check
				j = 0;
			}
		}		
	}
	

	
	public static void main(String[] args) {
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		System.out.print("Inserisci il valore di partenza\n -> ");
		inizio = s.nextInt();
		
		System.out.print("Inserisci il valore finale\n -> ");
		fine = s.nextInt();
		
		System.out.print("Inserisci il passo\n -> ");
		step = s.nextInt();

		System.out.print("Quanti Thread vuoi avviare\n -> ");
		nThreads = s.nextInt();		
		
		MultiThreadApp app = new MultiThreadApp();
		
		app.whoIsCounting();
				
	}
}









