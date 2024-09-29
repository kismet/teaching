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
package it.edu.marconipontedera.tepsit.status;

import java.time.Duration;
import java.util.Scanner;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Main {

	private static final long WATCHER_DELAY = 1000;

	public static void main(String[] args) {
		int t, n;
		Scanner cin = new Scanner(System.in);

		System.out.println("Quanti thread vuoi creare?\n -> ");
		t = cin.nextInt();

		System.out.println("Qual'è il valore massimo a cui contare?\n -> ");
		n = cin.nextInt();

		ValareCondiviso[] values = new ValareCondiviso[t];
		Thread[] threads = new Thread[t];
		
		for (int i = 0; i < threads.length; i++) {
			values[i] = new ValareCondiviso(0);
			ThreadContatore contatore = new ThreadContatore(n, values[i]);
			threads[i] = new Thread(contatore);
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		int running = t;
		while(running > 0) {
			System.out.println("MONITORING THREADS");
			System.out.println("------------------");
			for (int i = 0; i < threads.length; i++) {
				if (threads[i].isAlive() == false) {
					System.out.println(threads[i].getId()+" - COMPLETED");
					running--;
				} else {
					System.out.println(threads[i].getId()+" - "+values[i].current());					
				}
				System.out.println();
			}

			try {
				Thread.sleep(WATCHER_DELAY);
			} catch (InterruptedException e) {
			}
		}

		System.out.println("TUTTI I THREAD COMPLETATI");		
	}

}
