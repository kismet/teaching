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

import it.edu.marconipontedera.tepsit.buffer.Buffer;
import it.edu.marconipontedera.tepsit.buffer.CodaSemplice;

/**
 * Questa codice esegue la simulazione tre volte cambiando la dimensione del buffer
 * ogni volta<br>
 * Questa soluzione non è ingegnerizzata in maniera corretta, a partire dal fato che c'è
 * del codice duplicato tra {@link ConsumatoreTemporizzato} e {@link ProduttoreTemporizzato}
 * Si lascia al lettore di re-ingegnizzare la soluzione
 * 
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class MainProfile {

	private static final int[] PRODUCER_DELAY = new int[] {100, 250, 500, 1000};
	private static final int[] CONSUMER_DELAY = new int[] {200, 400, 600, 800, 1000};

	private static final int SIZE = 500;
	
	public static void main(String[] args) {
		int[] buffers = new int[] {5, 20, 50};

		for (int i = 0; i < buffers.length; i++) {
			produttoreConsumatoreBase(SIZE,new CodaSemplice(buffers[i]));		
		}
		
	}


	private static void produttoreConsumatoreBase(int n, Buffer buf) {
		
		ProduttoreTemporizzato p = new ProduttoreTemporizzato(buf, n);
		ConsumatoreTemporizzato c = new ConsumatoreTemporizzato(buf, n);
		p.setDelays(PRODUCER_DELAY);
		c.setDelays(CONSUMER_DELAY);

		Thread[] threads = new Thread[] { 
				new Thread(p,"Produttore"), new Thread(c, "Consumatore")
		};
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		boolean completed = false;
		long[] endTimes = new long[threads.length];
		while(!completed) {
			completed = true;
			for (int i = 0; i < threads.length; i++) {
				if(threads[i].isAlive()) {
					completed = false;
					break;
				} else {
					if(endTimes[i] == 0) endTimes[i] = System.currentTimeMillis();
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
		System.out.printf(
				"Produttore: %d ms \t Consumatore: %d ms",
				((endTimes[0]-startTime)/1000),
				((endTimes[1]-startTime)/1000)
		);
	}
	
}
