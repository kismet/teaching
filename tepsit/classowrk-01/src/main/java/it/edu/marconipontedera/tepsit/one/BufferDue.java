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
public class BufferDue implements Buffer {
	
	private static final int MAX_SIZE = 10;
	private int[] buf;
	private int inizio;
	private int fine;
	private int tot;

	public BufferDue() {
		buf = new int[MAX_SIZE];
		inizio = 0;
		fine = 0;
		tot = 0;
	}

	@Override
	public void add(int v) {
		int pos = -1;
		synchronized (buf) {
			while(tot == buf.length) {
				try {
					buf.wait();
				} catch (InterruptedException e) {
				}
			}
			pos = fine;
			fine = (fine + 1) % buf.length;
			tot++;
		}
		buf[pos] = v;
		synchronized (buf) {
			buf.notify();			
		}
	}

	@Override
	public int remove() {
		int v = -1;
		synchronized (buf) {
			while(tot == 0) {
				try {
					buf.wait();
				} catch (InterruptedException e) {
				}
			}
			v = buf[inizio];
			inizio = ( inizio + 1 ) % buf.length;
			tot--;
			buf.notify();
		}
		return v;
	}

}
