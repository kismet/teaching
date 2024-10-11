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

import java.util.concurrent.ThreadLocalRandom;

import it.edu.marconipontedera.tepsit.buffer.Buffer;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Consumatore implements Runnable {

	private int limit;
	private Buffer buffer;	
	
	public Consumatore(Buffer b, int l) {
		this.limit = l;
		this.buffer = b;
	}
	
	@Override
	public void run() {
		int i = 0;
		int pari = 0;
		int dispari = 0;
		while (i < limit) {
			int value = buffer.remove();
			System.out.println("Estratto "+value);
			i++;
			if (value % 2 == 0) pari++;
			else dispari++;
			System.out.printf("Pari: %d/%d\t Dispari: %d/%d\n",pari,i,dispari,i);
		}

	}

}
