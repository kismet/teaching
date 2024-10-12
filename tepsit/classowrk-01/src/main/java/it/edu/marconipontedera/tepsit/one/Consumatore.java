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
 * Un istanza di questa classe può essere usata all'interno di un {@link Thread}
 * per ricevere dati da un {@link Buffer} questa istanza termina dopo aver letto X valori oppure quando legge
 * un valore negativo
 * questa classe tiene traccia dei valori pari e dei valori dispari letti.
 * 
 * Il {@link Buffer} dai cui leggere può essere specificato al momento della creazione o
 * con il metodo {@link #setBuffer(Buffer)} prima di avviare il Thread vedi {@link Thread#start()}
 * 
 * Il numero di valori letti prima di terminare va specificato alla creazione vedi {@link #Consumatore(int)} e
 * {@link #Consumatore(Buffer, int)} in alternativa è {@value #DEFAULT_LIMIT}
 * 
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Consumatore implements Runnable {

	private static final int DEFAULT_LIMIT = 100;
	private int limit;
	private Buffer buffer;	
	
	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buf) {
		this.buffer = buf;
	}

	public Consumatore(Buffer b, int l) {
		this.limit = l;
		this.buffer = b;
	}
	
	public Consumatore() {
		this(null, DEFAULT_LIMIT);
	}
	
	public Consumatore(int limit) {
		this(null, limit);
	}
	
	
	
	@Override
	public void run() {
		int i = 0;
		int pari = 0;
		int dispari = 0;
		int value = 0;
		final String name = Thread.currentThread().getName();
		while (i < limit && value >= 0) {
			value = buffer.remove();
			i++;
			if (value % 2 == 0) pari++;
			else dispari++;
			System.out.println("Estratto "+value);
			System.out.printf("Pari: %d/%d\t Dispari: %d/%d\n",pari,i,dispari,i);
		}
		
		System.out.println();
		System.out.println(name+" - TERMINATO");
		System.out.printf("Pari: %d/%d\t Dispari: %d/%d\n",pari,i,dispari,i);
	}

}
