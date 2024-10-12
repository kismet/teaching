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

/**
 * 
 * Questa è la versione corretta del codice proposto al compito
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class BufferUno implements Buffer {
	
	private static final int DEFAULT_SIZE = 10;
	private int[] dati;
	private int primo;
	private int ultimo;
	private int elementi;

	public BufferUno() {
		dati = new int[DEFAULT_SIZE];
		primo = 0;
		ultimo = 0;
		elementi = 0;
	}

	@Override
	public void add(int v) {
		int pos = -1;
		synchronized (dati) {
			while(elementi == dati.length) {
				try {
					dati.wait();
				} catch (InterruptedException e) {
				}
			}
			pos = ultimo;
			ultimo = (ultimo + 1) % dati.length;
			elementi++;
		}
		dati[pos] = v;
		synchronized (dati) {
			dati.notify();
		}
	}

	@Override
	public int remove() {
		int pos = -1;
		synchronized (dati) {
			while(elementi == 0) {
				try {
					dati.wait();
				} catch (InterruptedException e) {
				}
			}
			pos = primo;
			primo = ( primo + 1 ) % dati.length;
			elementi--;
		}
		int value = dati[pos];
		synchronized (dati) {
			dati.notify();			
		}
		return value;
	}

}
