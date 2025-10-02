package pro.lenzi.tepsit.thread.share;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.xml.stream.events.StartDocument;

public class CountingTogether {
	
	private int inizio;
	private int fine;
	private int step;
	private int nThreads;

	public CountingTogether(int s,int e,int d,int n) {
		this.inizio = s;
		this.fine = e;
		this.step = d;
		this.nThreads = n;
	}
	
	public void everybodyCounting() {
		Thread[] threads = new Thread[nThreads];
		SharedCounter onceForAll = new SharedCounter(inizio,fine,step);
		for (int j = 0; j < threads.length; j++) {
			threads[j] = new Thread(onceForAll,"Contatore-"+j);
		}
		for (int j = 0; j < threads.length; j++) {
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
		
		CountingTogether app = new CountingTogether(2,12519,3,100);
		app.everybodyCounting();
		
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		System.out.print("Inserisci il valore di partenza\n -> ");
		int inizio = s.nextInt();
		
		System.out.print("Inserisci il valore finale\n -> ");
		int fine = s.nextInt();
		
		System.out.print("Inserisci il passo\n -> ");
		int step = s.nextInt();

		System.out.print("Quanti Thread vuoi avviare\n -> ");
		int nThreads = s.nextInt();		
		
		app = new CountingTogether(inizio,fine,step,nThreads);
		
		app.everybodyCounting();
				
	}
}









