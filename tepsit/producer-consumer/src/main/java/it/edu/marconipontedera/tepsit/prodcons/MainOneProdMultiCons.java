/*
* 
* Teaching material for High School of ITI 'G.Marconi'
* locate in Pontedera, Pisa, Italy 
*
* Material for Computer Science educational path 
*
* Copyright (C) 2024 Stefano Lenzi
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package it.edu.marconipontedera.tepsit.prodcons;

import java.util.Scanner;

import it.edu.marconipontedera.tepsit.buffer.Buffer;
import it.edu.marconipontedera.tepsit.buffer.CodaPrimaPrimi;
import it.edu.marconipontedera.tepsit.buffer.CodaSemplice;
import it.edu.marconipontedera.tepsit.buffer.CodaSempliceMulticast;
import it.edu.marconipontedera.tepsit.buffer.StackSemplice;

/**
 * Questa codice esegue la simulazione tre volte una per ogni
 * tipo di Buffer che era richiesto. 
 * Per farlo il sistema è stato progettato creando un unica
 * <b>Interfaccia</b> {@link Buffer} di cui sono data varie
 * implementazioni {@link CodaSemplice}, {@link StackSemplice} e {@link CodaPrimaPrimi}
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class MainOneProdMultiCons {

	private static final long WATCHER_DELAY = 1000;
	private static int N_CONSUMATORI = 5;
	
	public static void main(String[] args) {
		int n;
		Scanner cin = new Scanner(System.in);

		System.out.println("Quanti valori vuoi generare?\n -> ");
		n = cin.nextInt();
		
		Thread[] threads = new Thread[N_CONSUMATORI];
		Consumatore[] consumatori = new Consumatore[N_CONSUMATORI];
		
		for (int i = 0; i < threads.length; i++) {
			consumatori[i] = new Consumatore(n);
			threads[i] = new Thread(consumatori[i],"Consumatore-"+i);
		}
		
		CodaSempliceMulticast multi = new CodaSempliceMulticast(20, threads);
		for (int i = 0; i < consumatori.length; i++) {
			consumatori[i].setBuffer(multi);
		}
		
		
		Produttore p = new Produttore(multi, n);
		Thread produttore = new Thread(p, "Produttore");
		
		produttore.start();
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		/**
		 * RIFLESSIONI
		 * Cosa succede se aggiungiamo le seguenti righe di codice?
		 */
		/*
		Consumatore extra = new Consumatore(n);
		extra.setBuffer(multi);
		Thread extraThread = new Thread(extra,"Consumatore-Extra");
		extraThread.start();
		*/
		
		try {
			produttore.join();
		} catch (InterruptedException e) {
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
		
	}
}
