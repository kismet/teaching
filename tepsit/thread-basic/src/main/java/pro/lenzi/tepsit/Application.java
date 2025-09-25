package pro.lenzi.tepsit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {

	public void print(int i, int f, int p) {
		for (int x = i; x < f; x+=p) {
			System.out.println(x);
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		System.out.print("Inserisci il valore di partenza\n -> ");
		int i = s.nextInt();
		
		System.out.print("Inserisci il valore finale\n -> ");
		int f = s.nextInt();
		
		System.out.print("Inserisci il passo\n -> ");
		int p = s.nextInt();

		Application app = new Application();
		app.print(i,f,p);
		
		
		//Sbagliato perchè this non esiste in questo contesto
		//questo è un metodo di classe (statico) e non di un oggetto
		//this.print(i,f,p);
		
		
		//Il metodo print non è statico e quindi non può essere invocato
		//senza creare prima un oggetto della classe Application
		//print(i,f,p);
	}

}









