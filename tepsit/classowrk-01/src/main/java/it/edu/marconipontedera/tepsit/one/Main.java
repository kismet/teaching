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
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Main {

	
	
	//Numero dei produttori
	private static final int N_PRODUTTORI = 3;
	
	//Numero dei consumatori
	private static final int N_CONSUMATORI = 7;
	
	//Numero di dati da inviare
	private static final int N_DATI = 20;

	public static void main(String[] args) {
		//Qui potevo usare la classe il cui codice avevo sistemao al punto 1 dell'esercizio
		Buffer buf = new BufferTre();
		
		//In base alla tipologia Un prodotuttore ed un consumatore fino ad arrivare N produttori
		//ed M consumatori non faccio altro che adattare il valore N_PRODUTTORI e N_CONSUMATORI
		Thread[] produttori = new Thread[N_PRODUTTORI];
		for (int i = 0; i < produttori.length; i++) {
			produttori[i] = new Thread(new Produttore(buf, N_DATI),"Produttore-"+i);
			produttori[i].start();
		}
		
		Thread[] consumatori = new Thread[N_CONSUMATORI];
		for (int i = 0; i < consumatori.length; i++) {
			consumatori[i] = new Thread(new Consumatore(buf, N_DATI),"Consumatore-"+i);
			consumatori[i].start();
		}
		
		//A questo punto bisognava fare in modo che i produttori e consumatori terminassero 
		//per i produttori è facile perchè terminano dopo N_DATI invati, ma consumatori visto
		//che competono tra di loro potrebbero leggere meno dati e quindi serve un _segnale_
		//per indicare di terminare		
		if ( produttori.length <= consumatori.length ) {
			for (int i = 0; i < produttori.length; i++) {
				try {
					produttori[i].join();
				} catch (InterruptedException e) {
				}
			}
			boolean completed = false;
			while(!completed) {
				completed = true;
				for (int i = 0; i < consumatori.length; i++) {
					if( consumatori[i].isAlive() ) {
						buf.add(-1);
						completed = false;
					}
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
			}
		}
		
		//Come gestire il caso duale in cui non ci sono abbastanza consumatori?
		
	}
}
