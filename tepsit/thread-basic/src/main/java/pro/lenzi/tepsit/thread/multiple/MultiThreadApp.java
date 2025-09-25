package pro.lenzi.tepsit.thread.multiple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MultiThreadApp {
	
	private static int i;
	private static int f;
	private static int p;
	private static int t;

	
	public void startThemAll() {
		for (int j = 0; j < t; j++) {
			FriendlyCounter c = new FriendlyCounter(i,f,p);
			//c.beNice();
			Thread thread = new Thread(c,"Contatore-"+j);
			thread.start();
		}		
	}
	
	public void noZombieAllowed() {
		Thread[] threads = new Thread[t];
		for (int j = 0; j < threads.length; j++) {
			FriendlyCounter c = new FriendlyCounter(i,f,p);
			threads[j] = new Thread(c,"Contatore-"+j);
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
	
	public void whoIsCounting() {
		FriendlyCounter unico = new FriendlyCounter(i,f,p);
		for (int j = 0; j < t; j++) {
			Thread thread = new Thread(unico,"Contatore-"+j);
			thread.start();
		}		
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		System.out.print("Inserisci il valore di partenza\n -> ");
		i = s.nextInt();
		
		System.out.print("Inserisci il valore finale\n -> ");
		f = s.nextInt();
		
		System.out.print("Inserisci il passo\n -> ");
		p = s.nextInt();

		System.out.print("Quanti Thread vuoi avviare\n -> ");
		t = s.nextInt();		
				
	}
}









