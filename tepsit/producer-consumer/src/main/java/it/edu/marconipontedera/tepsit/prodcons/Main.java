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
public class Main {

	private static final long WATCHER_DELAY = 1000;
	
	public static void main(String[] args) {
		int n;
		Scanner cin = new Scanner(System.in);

		System.out.println("Quanti valori vuoi generare?\n -> ");
		n = cin.nextInt();

		produttoreConsumatoreBase(n,new CodaSemplice());
		produttoreConsumatoreLifo(n,new StackSemplice());
		produttoreConsumatorePriorita(n,new CodaPrimaPrimi());
		
		
	}

	private static void produttoreConsumatorePriorita(int n, Buffer buf) {
		Produttore p = new Produttore(buf, n);
		Consumatore c = new Consumatore(buf, n);

		Thread[] threads = new Thread[] { 
				new Thread(p,"Produttore"), new Thread(c, "Consumatore")
		};
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
	}

	private static void produttoreConsumatoreLifo(int n, Buffer buf) {
		Produttore p = new Produttore(buf, n);
		Consumatore c = new Consumatore(buf, n);

		Thread[] threads = new Thread[] { 
				new Thread(p,"Produttore"), new Thread(c, "Consumatore")
		};
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
	}

	private static void produttoreConsumatoreBase(int n, Buffer buf) {
		Produttore p = new Produttore(buf, n);
		Consumatore c = new Consumatore(buf, n);

		Thread[] threads = new Thread[] { 
				new Thread(p,"Produttore"), new Thread(c, "Consumatore")
		};
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
	}
	
}
