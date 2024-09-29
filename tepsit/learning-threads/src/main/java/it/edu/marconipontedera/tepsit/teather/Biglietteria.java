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
package it.edu.marconipontedera.tepsit.teather;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Biglietteria {
	
	private static final int FILE = 5;
	private static final int POSTI = 6;
	
	boolean[][] posti;
	
	public Biglietteria() {
		posti = new boolean[FILE][POSTI];
		for (int i = 0; i < posti.length; i++) {
			for (int j = 0; j < posti[i].length; j++) {
				posti[i][j] = true;
			}
		}
	}
	
	public Posto prenota(int fila, int pos) {		
		if (fila < 0 || fila >= FILE) 
			throw new IndexOutOfBoundsException("Posto fila "+fila+" inesistente in teatro");
		if (pos < 0 || pos >= POSTI) 
			throw new IndexOutOfBoundsException("Posto posizione "+pos+" inesistente in teatro");
		
		int minX=-1, minY=-1;
		int distanzaMinima = FILE*FILE+POSTI*POSTI;
		synchronized (posti) {
			for (int i = 0; i < FILE; i++) {
				for (int j = 0; j < POSTI; j++) {
					if(posti[i][j]) {
						int distanza = (fila-i)*(fila-i)+(pos-j)*(pos-j);
						if(distanza<distanzaMinima) {
							minX = i;
							minY = j;
							distanzaMinima = distanza;
						}
					}
				}		
			}
			if( minX < 0 ) return null;
			posti[minX][minY] = false;
		}
		return new Posto(minX, minY);
	}
	
	public int getNFile() {
		//Serve il synchronized su questo metodo?
		return posti.length;
	}

	public int getNPosti() {
		//Serve il synchronized su questo metodo?
		return posti[0].length;
	}
}
