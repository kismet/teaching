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
package it.edu.marconipontedera.tepsit.one;

import java.util.concurrent.ThreadLocalRandom;


/**
 * 
 * Un istanza di questa classe può essere usata all'interno di un {@link Thread}
 * per inviare su un {@link Buffer} specificato alla creazione un numero X di valori da inviare 
 * prima di terminare. I valori inviati sono compresi tra 0 e 1023. 
 * Il numero di valori da inviare è specificato al momento della creazione 
 * vedi {@link #Produttore(Buffer, int)}
 * 
 * L'invio dei dati nel buffer avviene con intervalli casuali tra Tx100ms dove T è compreso tra 1 e 10
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Produttore implements Runnable {

	private static final int RANGE = 1024;
	private static final long[] DELAYS = 
			new long[] {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

	private int limit;
	private Buffer buffer;
	
	
	public Produttore(Buffer b, int l) {
		this.limit = l;
		this.buffer = b;
	}
	
	@Override
	public void run() {
		int i = 0;
		ThreadLocalRandom random = ThreadLocalRandom.current();
		while (i < limit) {
			int value = random.nextInt(RANGE);
			buffer.add(value);
			i++;
			try {
				Thread.sleep(DELAYS[random.nextInt(DELAYS.length)]);
			} catch (InterruptedException e) {
			}
		}

	}

}
