package pro.lenzi.tepsit.thread.single;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SingleThreadApp {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		System.out.print("Inserisci il valore di partenza\n -> ");
		int i = s.nextInt();
		
		System.out.print("Inserisci il valore finale\n -> ");
		int f = s.nextInt();
		
		System.out.print("Inserisci il passo\n -> ");
		int p = s.nextInt();

		Contatore c = new Contatore(i,f,p);
		c.conta();

	}

}









